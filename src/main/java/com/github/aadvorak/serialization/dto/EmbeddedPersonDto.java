package com.github.aadvorak.serialization.dto;

import com.github.aadvorak.serialization.abstraction.CompactSerializable;
import com.github.aadvorak.serialization.input.ByteBufferWrapper;
import com.github.aadvorak.serialization.output.ByteArrayOutputStreamWrapper;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode
public class EmbeddedPersonDto implements CompactSerializable {

    private PersonDto person;

    @Override
    public byte[] serialize() {
        var stream = new ByteArrayOutputStreamWrapper();
        stream.writeSerializable(person);
        return stream.toByteArray();
    }

    public static EmbeddedPersonDto deserialize(byte[] bytes) {
        return deserialize(ByteBufferWrapper.wrap(bytes));
    }

    public static EmbeddedPersonDto deserialize(ByteBufferWrapper buffer) {
        var dto = new EmbeddedPersonDto();
        dto.setPerson(buffer.readNullable(PersonDto::deserialize));
        return dto;
    }
}
