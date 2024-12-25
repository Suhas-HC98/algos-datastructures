package com.shared.info.controller;

import com.shared.info.dto.Albums;
import com.shared.info.service.AlbumService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/album")
@AllArgsConstructor
public final class AlbumController {

    private final AlbumService albumService;

    @GetMapping(value = "/get-all")
    public List<Albums> getAlbums() {
        return albumService.getAlbums();
    }

    @GetMapping(value = "/get/{id}")
    public Albums getAlbum(@PathVariable(name = "id") Integer id) {
        return albumService.getAlbum(id);
    }
}
