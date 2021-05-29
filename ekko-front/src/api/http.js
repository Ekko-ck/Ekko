import axios from 'axios'
import store from '../store'

const CONTENT_TYPE_JSON = 'application/json;chatset=utf-8'
// const CONTENT_TYPE_FORM = 'multipart/form-data'

const getAuthorization = () => {
  if (process.env.VUE_APP_MODE && process.env.VUE_APP_MODE === 'local') {
    return `Bearer ${process.env.VUE_APP_JWT}`
  } else {
    return `Bearer ${store.getters.jwt}`
  }
}

const getConfig = (contentType) => {
  const config = {
    headers: {
      Authorization: getAuthorization()
    }
  }
  if (contentType) {
    config.headers['Content-Type'] = contentType
  }
  return config
}

const handleResponse = (res) => {
  if (res.status === 200 && res.data.resultCode === '0000') {
    return res.data.data
  } else {
    handleError(res)
    return null
  }
}

const handleError = (res) => {
  // TODO: 에러 팝업 처리
}

export default {
  async get (url) {
    const res = await axios.get(url, getConfig())
    return handleResponse(res)
  },
  async post (url, data) {
    const res = await axios.post(url, data, getConfig(CONTENT_TYPE_JSON))
    return handleResponse(res)
  },
  async put (url, data) {
    const res = await axios.put(url, data, getConfig(CONTENT_TYPE_JSON))
    return handleResponse(res)
  },
  async delete (url) {
    const res = await axios.delete(url)
    return handleResponse(res)
  }
}
