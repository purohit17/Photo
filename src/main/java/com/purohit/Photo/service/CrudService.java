package com.purohit.Photo.service;

import com.purohit.Photo.model.Photo;

public interface CrudService {
    public Iterable<Photo> insertDataToDb() throws Exception;
}
