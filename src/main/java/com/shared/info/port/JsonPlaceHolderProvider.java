package com.shared.info.port;

import com.shared.info.dto.Albums;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "json-placeholder", url = "${com.shared.info.url.json-placeholder}")
public interface JsonPlaceHolderProvider {

    @GetMapping(value = "/albums")
    List<Albums> getAlbums();


    @GetMapping(value = "/albums/{id}")
    Albums getAlbum(@PathVariable(name = "id") Integer id);
}
