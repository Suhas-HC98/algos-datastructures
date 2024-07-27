package com.shared.info.service.impl;

import com.shared.info.dto.Albums;
import com.shared.info.port.JsonPlaceHolderProvider;
import com.shared.info.service.AlbumService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AlbumServiceImpl implements AlbumService {

    private final JsonPlaceHolderProvider jsonPlaceHolderProvider;

    @Override
    public List<Albums> getAlbums() {
        return jsonPlaceHolderProvider.getAlbums();
    }

    @Override
    public Albums getAlbum(Integer id) {
        return jsonPlaceHolderProvider.getAlbum(id);
    }
}
