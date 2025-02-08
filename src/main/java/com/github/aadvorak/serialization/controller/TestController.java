package com.github.aadvorak.serialization.controller;

import com.github.aadvorak.serialization.dto.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/test", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
public class TestController {

    @GetMapping("/primitives")
    public byte[] getPrimitives() {
        return createPrimitiveDto()
                .serialize();
    }

    @GetMapping("/person")
    public byte[] getPerson() {
        return createPersonDto()
                .serialize();
    }

    @GetMapping("/embedded-list")
    public byte[] getList() {
        return new EmbeddedListDto()
                .setPersons(List.of(createPersonDto(), createPersonDto()))
                .serialize();
    }

    @GetMapping("/embedded-string-map")
    public byte[] getStringMap() {
        return new EmbeddedStringMapDto()
                .setStringMap(Map.of(
                        "first", createPersonDto(),
                        "second", createPersonDto()
                ))
                .serialize();
    }

    @GetMapping("/embedded-integer-map")
    public byte[] getIntMap() {
        return new EmbeddedIntegerMapDto()
                .setIntegerMap(Map.of(
                        1, createPersonDto(),
                        2, createPersonDto()
                ))
                .serialize();
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
}
