package com.shared.info.controller.IT;

import com.shared.info.AutoConfigureTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureTest
class AlbumControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_return_all_albums() throws Exception {
        var albumResponse = mockMvc.perform(get("/album/get-all")
                .contentType(APPLICATION_JSON));
        albumResponse.andExpect(status().is2xxSuccessful());
        albumResponse.andExpect(jsonPath("$.length()").value(1));
        albumResponse.andExpect(jsonPath("$.[0].title").value("quidem molestiae enim"));
    }

    @Test
    void should_return_specific_albums_when_id_is_passed() throws Exception {
        var albumResponse = mockMvc.perform(get("/album/get/" + 12)
                .contentType(APPLICATION_JSON));
        albumResponse.andExpect(status().is2xxSuccessful());
        albumResponse.andExpect(jsonPath("$.userId").value("1"));
        albumResponse.andExpect(jsonPath("$.id").value("12"));
        albumResponse.andExpect(jsonPath("$.title").value("omnis laborum odio"));
    }
}