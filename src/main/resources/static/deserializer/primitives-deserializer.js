const PrimitivesDeserializer = {
  deserialize(input) {
    const booleanPrimitive = DeserializerBase.readBoolean(input)
    const intPrimitive = DeserializerBase.readInt(input)
    const longPrimitive = DeserializerBase.readLong(input)
    const floatPrimitive = DeserializerBase.readFloat(input)
    const doublePrimitive = DeserializerBase.readDouble(input)
    return {
      booleanPrimitive,
      intPrimitive,
      longPrimitive,
      floatPrimitive,
      doublePrimitive
    }
  }
}
