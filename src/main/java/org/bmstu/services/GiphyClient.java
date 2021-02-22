package org.bmstu.services;

import org.bmstu.model.GifResponse;
import org.bmstu.properties.GiphyProperties;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "giphyClient", url = GiphyProperties.GIF_URL)
public interface GiphyClient {

    @GetMapping(GiphyProperties.APP_ID + "&q={search_text}&offset={number}&limit=1")
    GifResponse searchGif(@PathVariable String search_text, @PathVariable int number);
}