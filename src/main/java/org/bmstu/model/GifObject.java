package org.bmstu.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GifObject {

    @JsonProperty("embed_url")
    private String url;

    public String getUrl() {
        return url;
    }
}
