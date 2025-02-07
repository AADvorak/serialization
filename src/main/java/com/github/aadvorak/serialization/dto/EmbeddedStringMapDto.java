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
public class EmbeddedStringMapDto implements CompactSerializable {

    private Map<String, PersonDto> stringMap;

    @Override
    public byte[] serialize() {
        var stream = new ByteArrayOutputStreamWrapper();
        stream.writeStringMapOfSerializable(stringMap);
        return stream.toByteArray();
    }

    public static EmbeddedStringMapDto deserialize(byte[] bytes) {
        return EmbeddedStringMapDto.deserialize(ByteBufferWrapper.wrap(bytes));
    }

    public static EmbeddedStringMapDto deserialize(ByteBufferWrapper buffer) {
        var dto = new EmbeddedStringMapDto();
        dto.setStringMap(buffer.readMap(buffer::readString, PersonDto::deserialize));
        return dto;
    }
}
