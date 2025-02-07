const PersonDeserializer = {
  deserialize(input) {
    const firstName = DeserializerBase.readString(input)
    const lastName = DeserializerBase.readString(input)
    const age = DeserializerBase.readInt(input)

    return {firstName, lastName, age}
  }
}
