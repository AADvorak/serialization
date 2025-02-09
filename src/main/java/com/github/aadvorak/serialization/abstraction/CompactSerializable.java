package com.github.aadvorak.serialization.abstraction;

import com.github.aadvorak.serialization.output.ByteArrayOutputStreamWrapper;

public interface CompactSerializable {
    void writeToStream(ByteArrayOutputStreamWrapper stream);

    default byte[] serialize() {
        var stream = new ByteArrayOutputStreamWrapper();
        writeToStream(stream);
        return stream.toByteArray();
    }
}
