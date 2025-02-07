package com.github.aadvorak.serialization.controller;

import com.github.aadvorak.serialization.dto.EmbeddedListDto;
import com.github.aadvorak.serialization.dto.EmbeddedStringMapDto;
import com.github.aadvorak.serialization.dto.PersonDto;
import com.github.aadvorak.serialization.dto.PrimitivesDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping(value = "/primitives", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] getPrimitives() {
        return createPrimitiveDto()
                .serialize();
    }

    @GetMapping(value = "/person", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] getPerson() {
        return createPersonDto()
                .serialize();
    }

    @GetMapping(value = "/embedded-list", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] getList() {
        return new EmbeddedListDto()
                .setPersons(List.of(createPersonDto(), createPersonDto()))
                .serialize();
    }

    @GetMapping(value = "/embedded-string-map", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] getStringMap() {
        return new EmbeddedStringMapDto()
                .setStringMap(Map.of(
                        "first", createPersonDto(),
                        "second", createPersonDto()
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
