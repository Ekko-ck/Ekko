module.exports = {
  transpileDependencies: [
    'vuetify'
  ],
  devServer: {
    proxy: {
      '^/api': {
        target: 'http://133.186.223.59:8310',
        changeOrigin: true
      }
    }
  }
}
