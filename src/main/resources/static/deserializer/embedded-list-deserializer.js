const EmbeddedListDeserializer = {
  deserialize(input) {
    let persons = DeserializerBase.readArray(input, PersonDeserializer.deserialize)
    return {persons}
  }
}
