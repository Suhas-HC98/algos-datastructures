package com.shared.info.service;

import com.shared.info.dto.InternetProtocol;
import com.shared.info.service.impl.JsonContentServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class JsonContentServiceImplTest {

    private final JsonContentServiceImpl service = new JsonContentServiceImpl();

    @Test
    void should_parse_json_file_and_return_the_date_when_file_name_is_passed() throws Exception {
        var actualResponse = service.fetchJsonData(InternetProtocol.class, "InternetProtocol");
        assertFalse(actualResponse.isEmpty());
    }

}
