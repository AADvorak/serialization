package com.github.aadvorak.serialization.dto;

import com.github.aadvorak.serialization.abstraction.CompactSerializable;
import com.github.aadvorak.serialization.input.ByteBufferWrapper;
import com.github.aadvorak.serialization.output.ByteArrayOutputStreamWrapper;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode
public class EmbeddedListDto implements CompactSerializable {

    private List<PersonDto> persons;

    @Override
    public void writeToStream(ByteArrayOutputStreamWrapper stream) {
        stream.writeCollectionOfSerializable(persons);
    }

    public static EmbeddedListDto deserialize(byte[] bytes) {
        return EmbeddedListDto.deserialize(ByteBufferWrapper.wrap(bytes));
    }

    public static EmbeddedListDto deserialize(ByteBufferWrapper buffer) {
        var dto = new EmbeddedListDto();
        dto.setPersons(buffer.readList(PersonDto::deserialize));
        return dto;
    }
}
