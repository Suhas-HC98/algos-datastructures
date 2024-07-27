package com.shared.info.service.impl;

import com.shared.info.port.JsonPlaceHolderProvider;
import com.shared.info.service.AlbumService;
import org.junit.jupiter.api.Test;

import static com.shared.info.vo.TestUtils.albums;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AlbumServiceImplTest {

    private final JsonPlaceHolderProvider gateway = mock(JsonPlaceHolderProvider.class);
    private final AlbumService service = new AlbumServiceImpl(gateway);

    @Test
    void should_return_all_albums_when_gateway_is_called() {
        when(gateway.getAlbums()).thenReturn(albums());
        var albums = service.getAlbums();
        assertEquals(2, albums.size());
        assertEquals(1, albums.get(0).userId());
        verify(gateway).getAlbums();
    }

    @Test
    void should_return_album_when_albumId_is_passed() {
        var id = 2;
        when(gateway.getAlbum(id)).thenReturn(albums().get(1));
        var albums = service.getAlbum(id);
        assertEquals(1, albums.userId());
        verify(gateway).getAlbum(id);
        verify(gateway, never()).getAlbums();
    }
}