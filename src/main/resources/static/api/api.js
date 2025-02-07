const Api = {
  async getInput(url) {
    const response = await fetch(url)
    const data = await response.arrayBuffer()
    return {
      data,
      dataView: new DataView(data),
      offset: 0
    }
  }
}
