package com.shared.info.service;

import com.shared.info.dto.Albums;

import java.util.List;

public interface AlbumService {

    List<Albums> getAlbums();

    Albums getAlbum(Integer id);
}
