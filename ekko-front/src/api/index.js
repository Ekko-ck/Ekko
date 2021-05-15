import axios from 'axios'

export default {
  getTest: async function () {
    const res = await axios.get('/api/sample/hello2/helloworld')
    return res.data
  }
}
