package com.github.aadvorak.serialization.input;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class ByteBufferWrapper {
    private final ByteBuffer buffer;

    private ByteBufferWrapper(ByteBuffer buffer) {
        this.buffer = buffer;
    }

    public String readString() {
        int length = buffer.getInt();
        byte[] bytes = new byte[length];
        buffer.get(bytes);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public boolean readBoolean() {
        return buffer.get() != 0;
    }

    public int readInt() {
        return buffer.getInt();
    }

    public long readLong() {
        return buffer.getLong();
    }

    public float readFloat() {
        return buffer.getFloat();
    }

    public double readDouble() {
        return buffer.getDouble();
    }

    public <K, V> Map<K, V> readMap(
            Supplier<K> keyReader,
            Function<ByteBufferWrapper, V> valueReader
    ) {
        if (readBoolean()) {
            Map<K, V> map = new HashMap<>();
            int size = readInt();
            for (int i = 0; i < size; i++) {
                try {
                    map.put(keyReader.get(), valueReader.apply(this));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            return map;
        }
        return null;
    }

    public <V> List<V> readList(Function<ByteBufferWrapper, V> valueReader) {
        return readNullable(buffer -> {
            List<V> list = new ArrayList<>();
            int size = readInt();
            for (int i = 0; i < size; i++) {
                list.add(valueReader.apply(this));
            }
            return list;
        });
    }

    public <V> V readNullable(Function<ByteBufferWrapper, V> valueReader) {
        if (readBoolean()) {
            try {
                return valueReader.apply(this);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public static ByteBufferWrapper wrap(byte[] bytes) {
        return new ByteBufferWrapper(ByteBuffer.wrap(bytes));
    }
}
