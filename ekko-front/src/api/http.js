import axios from 'axios'
import store from '../store'
import router from '../router'

const CONTENT_TYPE_JSON = 'application/json;chatset=utf-8'
// const CONTENT_TYPE_FORM = 'multipart/form-data'

// Add a response interceptor
axios.interceptors.response.use((response) => {
  return response
}, async (error) => {
  const originalRequest = error.config
  // http 401을 응답받으면 리프레시토큰으로 토큰을 재발급하고, request를 재요청
  if (error.response.status === 401 && !originalRequest.isRetry) {
    originalRequest.isRetry = true // 재요청여부. 기존요청을 다시 요청했을때, 401이 발생하면 무한 리쿼스트가 발생하므로 무한 재요청안하게끔 추가
    const response = await store.dispatch('auth/refreshToken')
    if (response == null) {
      router.push('Login')
    }
    originalRequest.headers.Authorization = `Bearer ${store.getters['auth/jwt']}`
    return axios(originalRequest)
  }
})

const getAuthorization = (url) => {
  // refreshtoken을 받을때는 refreshtoken을 헤더에 set
  if (url === '/api/user/auth/refreshtoken') {
    return `Bearer ${store.getters['auth/refreshToken']}`
  }
  return `Bearer ${store.getters['auth/jwt']}`
}

const getConfig = (contentType, url) => {
  const config = {
    headers: {
      Authorization: getAuthorization(url)
    }
  }
  if (contentType) {
    config.headers['Content-Type'] = contentType
  }
  return config
}

const handleResponse = (res, isShowPopup) => {
  if (res.status === 200 && res.data.resultCode === '0000') {
    return res.data.data
  } else {
    handleError(res, isShowPopup)
    return null
  }
}

const handleError = async (res, isShowPopup) => {
  const errorCode = res.status === 200 ? res.data.resultCode : res.status
  const errorMessage = res.status === 200 ? !res.data.resultMessage ? '에러가 발생했습니다.' : res.data.resultMessage : '에러가 발생했습니다.'
  if (isShowPopup) {
    await showErrorPopup(errorCode, errorMessage)
  }
  // Do something...
}

const showErrorPopup = (errorCode, errorMessage) => {
  return new Promise((resolve) => {
    const popup = { title: '에러', body: `${errorMessage} [${errorCode}]` }
    popup.resolve = resolve
    store.dispatch('popup/alert', popup)
  })
}

export default {
  async get (url, data, isShowPopup) {
    try {
      const config = getConfig(null, url)
      config.params = data
      const res = await axios.get(url, config)
      return handleResponse(res, isShowPopup)
    } catch (err) {
      return handleResponse(err, isShowPopup)
    }
  },
  async post (url, data, isShowPopup) {
    try {
      const res = await axios.post(url, data, getConfig(CONTENT_TYPE_JSON, url))
      return handleResponse(res, isShowPopup)
    } catch (err) {
      return handleResponse(err, isShowPopup)
    }
  },
  async put (url, data, isShowPopup) {
    try {
      const res = await axios.put(url, data, getConfig(CONTENT_TYPE_JSON, url))
      return handleResponse(res, isShowPopup)
    } catch (err) {
      return handleResponse(err, isShowPopup)
    }
  },
  async delete (url, isShowPopup) {
    try {
      const res = await axios.delete(url, getConfig(CONTENT_TYPE_JSON, url))
      return handleResponse(res, isShowPopup)
    } catch (err) {
      return handleResponse(err, isShowPopup)
    }
  }
}
