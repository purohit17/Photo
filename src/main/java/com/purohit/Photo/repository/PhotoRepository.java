package com.purohit.Photo.repository;

import com.purohit.Photo.model.Photo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PhotoRepository extends CrudRepository<Photo,Integer> {
    public Optional<Photo> findByPhotoIdAndAlbumId(int photoId,int albumId);

    public List<Photo> findByAlbumId(int albumId);
}
