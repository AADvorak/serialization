const EmbeddedStringMapDeserializer = {
  deserialize(input) {
    let stringMap = DeserializerBase.readMap(input,
        key => DeserializerBase.readString(key), PersonDeserializer.deserialize)
    return {stringMap}
  }
}
