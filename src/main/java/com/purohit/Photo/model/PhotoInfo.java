package com.purohit.Photo.model;

import lombok.Data;

@Data
public class PhotoInfo {
    private int photoId;
    private String thumbNailUrl;
    private String title;
    private String url;

}
