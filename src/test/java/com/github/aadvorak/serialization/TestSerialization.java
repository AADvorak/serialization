package com.github.aadvorak.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.aadvorak.serialization.dto.*;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class TestSerialization {

    @Test
    public void primitivesDtoSerialization() {
        var dto = createPrimitiveDto();
        var jsonBytes = getJsonBytes(dto);
        System.out.println("Json length = " + jsonBytes.length);

        var serialized = dto.serialize();
        System.out.println("Serialized length = " + serialized.length);
        var deserialized = PrimitivesDto.deserialize(serialized);
        assert dto.equals(deserialized);
    }

    @Test
    public void personDtoSerialization() {
        var dto = createPersonDto();
        var jsonBytes = getJsonBytes(dto);
        System.out.println("Json length = " + jsonBytes.length);

        var serialized = dto.serialize();
        System.out.println("Serialized length = " + serialized.length);
        var deserialized = PersonDto.deserialize(serialized);
        assert dto.equals(deserialized);
    }

    @Test
    public void embeddedPersonDtoSerialization() {
        var dto = new EmbeddedPersonDto().setPerson(createPersonDto());
        var jsonBytes = getJsonBytes(dto);
        System.out.println("Json length = " + jsonBytes.length);

        var serialized = dto.serialize();
        System.out.println("Serialized length = " + serialized.length);
        var deserialized = EmbeddedPersonDto.deserialize(serialized);
        assert dto.equals(deserialized);
    }

    @Test
    public void embeddedIntegerMapDtoSerialization() {
        var dto = new EmbeddedIntegerMapDto().setIntegerMap(
                Map.of(
                        1, createPersonDto(),
                        2, createPersonDto()
                )
        );
        var jsonBytes = getJsonBytes(dto);
        System.out.println("Json length = " + jsonBytes.length);

        var serialized = dto.serialize();
        System.out.println("Serialized length = " + serialized.length);
        var deserialized = EmbeddedIntegerMapDto.deserialize(serialized);
        assert dto.equals(deserialized);
    }

    @Test
    public void embeddedStringMapDtoSerialization() {
        var dto = new EmbeddedStringMapDto().setStringMap(
                Map.of(
                        "1", createPersonDto(),
                        "2", createPersonDto()
                )
        );
        var jsonBytes = getJsonBytes(dto);
        System.out.println("Json length = " + jsonBytes.length);

        var serialized = dto.serialize();
        System.out.println("Serialized length = " + serialized.length);
        var deserialized = EmbeddedStringMapDto.deserialize(serialized);
        assert dto.equals(deserialized);
    }

    @Test
    public void embeddedListDtoSerialization() {
        var dto = new EmbeddedListDto().setPersons(List.of(createPersonDto(), createPersonDto()));
        var jsonBytes = getJsonBytes(dto);
        System.out.println("Json length = " + jsonBytes.length);

        var serialized = dto.serialize();
        System.out.println("Serialized length = " + serialized.length);
        var deserialized = EmbeddedListDto.deserialize(serialized);
        assert dto.equals(deserialized);
    }

    private PersonDto createPersonDto() {
        return new PersonDto()
                .setFirstName("John")
                .setLastName("Doe")
                .setAge(25);
    }

    private PrimitivesDto createPrimitiveDto() {
        return new PrimitivesDto()
                .setBooleanPrimitive(true)
                .setIntPrimitive(314)
                .setLongPrimitive(456892L)
                .setFloatPrimitive((float) Math.PI)
                .setDoublePrimitive(Math.PI);
    }

    private byte[] getJsonBytes(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object).getBytes(StandardCharsets.UTF_8);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
