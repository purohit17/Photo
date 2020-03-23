package com.purohit.Photo.controller;

import com.purohit.Photo.model.Album;
import com.purohit.Photo.model.AlbumInfo;
import com.purohit.Photo.model.Photo;
import com.purohit.Photo.model.PhotoInfo;
import com.purohit.Photo.repository.AlbumRepository;
import com.purohit.Photo.repository.PhotoRepository;
import com.purohit.Photo.service.CrudService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CrudController {

    @Autowired
    private CrudService crudService;
    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    PhotoRepository photoRepository;

    @GetMapping("/intiate/insert")
    public Iterable<Photo> insertDataToDb(){
        try {
            return crudService.insertDataToDb();
        }catch (Exception e){
            log.error("theres some error {}",e.getMessage());
        }
        return new ArrayList<>();
    }

    @GetMapping("/search")
    public Object search(HttpServletRequest httpServletRequest){
        String type=httpServletRequest.getParameter("type");
        String id=httpServletRequest.getParameter("id");
        String albumId=httpServletRequest.getParameter("album");
        if(type.equals("album")){
            return albumRepository.findById(Integer.parseInt(id)).get();
        }
        Photo photo=photoRepository.findByPhotoIdAndAlbumId(Integer.parseInt(id),Integer.parseInt(albumId)).get();
        photo.setAlbumPrimaryKey(photo.getAlbum().getId());
        photo.setId(null);
        return photo;
    }

    @GetMapping("/modified-search")
    public AlbumInfo getAlbumInfo(HttpServletRequest httpServletRequest){
        String albumId=httpServletRequest.getParameter("album");
        List<Photo> photoList=photoRepository.findByAlbumId(Integer.parseInt(albumId));
        AlbumInfo albumInfo = new AlbumInfo();
        List<PhotoInfo> photoInfoList=new ArrayList<>();
        for(Photo photo:photoList){
            PhotoInfo photoInfo = new PhotoInfo();
            photoInfo.setPhotoId(photo.getPhotoId());
            photoInfo.setThumbNailUrl(photo.getThumbNailUrl());
            photoInfo.setTitle(photo.getTitle());
            photoInfo.setUrl(photo.getUrl());
            photoInfoList.add(photoInfo);
        }
        Album album=photoList.get(0).getAlbum();
        albumInfo.setId(album.getId());
        albumInfo.setTitle(album.getTitle());
        albumInfo.setPhotoInfos(photoInfoList);
        return albumInfo;

    }

}
