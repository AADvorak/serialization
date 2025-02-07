const DeserializerBase = {
  readBoolean(input) {
    const value = input.dataView.getInt8(input.offset)
    input.offset++
    return value > 0
  },

  readInt(input) {
    const value = input.dataView.getInt32(input.offset)
    input.offset += 4
    return value
  },

  readLong(input) {
    const value = Number(input.dataView.getBigInt64(input.offset))
    input.offset += 8
    return value
  },

  readFloat(input) {
    const value = input.dataView.getFloat32(input.offset)
    input.offset += 4
    return value
  },

  readDouble(input) {
    const value = input.dataView.getFloat64(input.offset)
    input.offset += 8
    return value
  },

  readString(input) {
    const length = this.readInt(input)
    const bytes = new Uint8Array(input.data, input.offset, length)
    input.offset += length
    return new TextDecoder().decode(bytes)
  },

  readArray(input, readValueFunc) {
    let array = null
    if (DeserializerBase.readBoolean(input)) {
      array = []
      const length = DeserializerBase.readInt(input)
      for (let i = 0; i < length; i++) {
        array.push(readValueFunc(input))
      }
    }
    return array
  },

  readMap(input, readKeyFunc, readValueFunc) {
    let map = null
    if (DeserializerBase.readBoolean(input)) {
      map = {}
      const length = DeserializerBase.readInt(input)
      for (let i = 0; i < length; i++) {
        map[String(readKeyFunc(input))] = readValueFunc(input)
      }
    }
    return map
  }
}
