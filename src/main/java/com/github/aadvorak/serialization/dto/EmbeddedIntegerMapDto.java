package com.github.aadvorak.serialization.dto;

import com.github.aadvorak.serialization.abstraction.CompactSerializable;
import com.github.aadvorak.serialization.input.ByteBufferWrapper;
import com.github.aadvorak.serialization.output.ByteArrayOutputStreamWrapper;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Map;

@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode
public class EmbeddedIntegerMapDto implements CompactSerializable {

    private Map<Integer, PersonDto> integerMap;

    @Override
    public byte[] serialize() {
        var stream = new ByteArrayOutputStreamWrapper();
        stream.writeIntegerMapOfSerializable(integerMap);
        return stream.toByteArray();
    }

    public static EmbeddedIntegerMapDto deserialize(byte[] bytes) {
        return EmbeddedIntegerMapDto.deserialize(ByteBufferWrapper.wrap(bytes));
    }

    public static EmbeddedIntegerMapDto deserialize(ByteBufferWrapper buffer) {
        var dto = new EmbeddedIntegerMapDto();
        dto.setIntegerMap(buffer.readMap(buffer::readInt, PersonDto::deserialize));
        return dto;
    }
}
