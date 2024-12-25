package com.shared.info.controller;

import com.shared.info.service.AlbumService;
import org.junit.jupiter.api.Test;

import static com.shared.info.vo.TestUtils.albums;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AlbumControllerTest {

    private final AlbumService service = mock(AlbumService.class);
    private final AlbumController controller = new AlbumController(service);

    @Test
    void should_return_all_albums_when_service_is_called() {
        when(service.getAlbums()).thenReturn(albums());
        var albums = controller.getAlbums();
        assertEquals(2, albums.size());
        assertEquals(1, albums.get(0).userId());
        verify(service).getAlbums();
    }

    @Test
    void should_return_album_when_albumId_is_passed() {
        var id = 2;
        when(service.getAlbum(id)).thenReturn(albums().get(1));
        var albums = controller.getAlbum(id);
        assertEquals(1, albums.userId());
        verify(service).getAlbum(id);
        verify(service, never()).getAlbums();
    }

}