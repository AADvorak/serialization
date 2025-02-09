package com.github.aadvorak.serialization.dto;

import com.github.aadvorak.serialization.abstraction.CompactSerializable;
import com.github.aadvorak.serialization.input.ByteBufferWrapper;
import com.github.aadvorak.serialization.output.ByteArrayOutputStreamWrapper;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Objects;

@Getter
@Setter
@Accessors(chain = true)
public class PrimitivesDto implements CompactSerializable {

    private boolean booleanPrimitive;

    private int intPrimitive;

    private long longPrimitive;

    private float floatPrimitive;

    private double doublePrimitive;

    @Override
    public void writeToStream(ByteArrayOutputStreamWrapper stream) {
        stream.writeBoolean(booleanPrimitive);
        stream.writeInt(intPrimitive);
        stream.writeLong(longPrimitive);
        stream.writeFloat(floatPrimitive);
        stream.writeDouble(doublePrimitive);
    }

    public static PrimitivesDto deserialize(byte[] bytes) {
        return deserialize(ByteBufferWrapper.wrap(bytes));
    }

    public static PrimitivesDto deserialize(ByteBufferWrapper buffer) {
        return new PrimitivesDto()
                .setBooleanPrimitive(buffer.readBoolean())
                .setIntPrimitive(buffer.readInt())
                .setLongPrimitive(buffer.readLong())
                .setFloatPrimitive(buffer.readFloat())
                .setDoublePrimitive(buffer.readDouble());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PrimitivesDto that = (PrimitivesDto) o;
        return booleanPrimitive == that.booleanPrimitive
                && intPrimitive == that.intPrimitive
                && longPrimitive == that.longPrimitive
                && Float.compare(floatPrimitive, that.floatPrimitive) == 0
                && Double.compare(doublePrimitive, that.doublePrimitive) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(booleanPrimitive, intPrimitive, longPrimitive, floatPrimitive, doublePrimitive);
    }
}
