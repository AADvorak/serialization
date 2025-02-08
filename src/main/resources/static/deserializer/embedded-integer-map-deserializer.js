const EmbeddedIntegerMapDeserializer = {
  deserialize(input) {
    let integerMap = DeserializerBase.readMap(input,
        key => DeserializerBase.readInt(key), PersonDeserializer.deserialize)
    return {integerMap}
  }
}
