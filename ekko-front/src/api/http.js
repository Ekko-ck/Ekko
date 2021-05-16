import axios from 'axios'

const CONTENT_TYPE_JSON = 'application/json;chatset=utf-8'
const CONTENT_TYPE_FORM = 'multipart/form-data'

const getAuthorization = () => {
  if (process.env.VUE_APP_MODE && process.env.VUE_APP_MODE === 'local') {
    return `Bearer ${process.env.VUE_APP_JWT}`
  } else {
    return `Bearer ` // TODO: store에서 조회
  }
}

const getConfig = (contentType) => {
  let config = {
    headers: {
      'Authorization': getAuthorization()
    }
  }
  if (contentType) {
    config.headers['Content-Type'] = contentType
  }
}

export default {
  get (url) {
    return axios.get(url, getConfig())
  },
  post (url, data) {
    return axios.post(url, data, getConfig(CONTENT_TYPE_JSON))
  },
  put (url, data) {
    return axios.put(url, data, getConfig(CONTENT_TYPE_JSON))
  },
  delete (url) {
    return axios.delete(url, jwt)
  }
}
