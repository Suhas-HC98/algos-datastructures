package com.shared.info.service;

import com.shared.info.dto.InternetProtocol;
import com.shared.info.service.impl.TextContentParserServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TextContentParserServiceImplTest {

    private final TextContentParserServiceImpl service = new TextContentParserServiceImpl();

    @Test
    void should_throw_null_pointer_when_illegal_argument_is_passed() {
        assertThrows(NullPointerException.class, () -> service.parseTextContent("demo"));
    }

    @Test
    void should_return_class_fields_when_class_name_is_passed() {
        var actualResponse = service.getClassFieldsAsString(InternetProtocol.class);
        assertFalse(actualResponse.isEmpty());
    }

}
