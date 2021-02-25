package org.bmstu.services;

import org.bmstu.model.GifResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "giphyClient", url = "${giphy.service.url}")
public interface GiphyClient {

    @GetMapping("?api_key=" + "${giphy.service.app_id}" + "&q={search_text}&offset={number}&limit=1")
    GifResponse searchGif(@PathVariable String search_text, @PathVariable int number);
}