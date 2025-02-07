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
public class PersonDto implements CompactSerializable {

    private String firstName;

    private String lastName;

    private int age;

    @Override
    public byte[] serialize() {
        var stream = new ByteArrayOutputStreamWrapper();
        stream.writeString(firstName);
        stream.writeString(lastName);
        stream.writeInt(age);
        return stream.toByteArray();
    }

    public static PersonDto deserialize(byte[] bytes) {
        return deserialize(ByteBufferWrapper.wrap(bytes));
    }

    public static PersonDto deserialize(ByteBufferWrapper buffer) {
        return new PersonDto()
                .setFirstName(buffer.readString())
                .setLastName(buffer.readString())
                .setAge(buffer.readInt());
    }
}
