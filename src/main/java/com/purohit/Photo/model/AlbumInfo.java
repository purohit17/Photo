package com.purohit.Photo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class AlbumInfo {
    private int id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("photos")
    private List<PhotoInfo> photoInfos;

}
