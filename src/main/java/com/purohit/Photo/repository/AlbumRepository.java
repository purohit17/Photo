package com.purohit.Photo.repository;

import com.purohit.Photo.model.Album;
import org.springframework.data.repository.CrudRepository;

public interface AlbumRepository extends CrudRepository<Album,Integer> {

}
