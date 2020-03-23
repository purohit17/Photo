package com.purohit.Photo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.purohit.Photo.model.Album;
import com.purohit.Photo.model.Photo;
import com.purohit.Photo.repository.PhotoRepository;
import com.purohit.Photo.util.MappingUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class CrudServiceImpl implements CrudService {
    @Autowired
    @Qualifier(value="restClient")
    private RestTemplate restTemplate;
    @Autowired
    private Environment environment;
    @Autowired
    private PhotoRepository photoRepository;

    @Override
    @Transactional
    public Iterable<Photo> insertDataToDb() throws Exception {
        String scheme=environment.getProperty("purohit.photo.crudserviceimpl.scheme","https");
        String host=environment.getProperty("purohit.photo.crudserviceimpl.host","jsonplaceholder.typicode.com");
        String albumPath=environment.getProperty("purohit.photo.crudserviceimpl.albumpath","albums");
        String photoPath=environment.getProperty("purohit.photo.crudserviceimpl.photoPath","photos");
        String url= MappingUtil.buildUrl(scheme,host,albumPath,null);
        log.info("the url is {}",url);
        ResponseEntity<List<Album>> albumListResponseEntity=restTemplate.exchange(url, HttpMethod.GET,null,new ParameterizedTypeReference<List<Album>>(){});
        log.info("response recieved is {}",albumListResponseEntity.getBody());
        List<Album> albums=albumListResponseEntity.getBody();
        List<Photo> photos=new ArrayList<>();
        for(Album album:albums) {
            Map<String, String> queryMap = new HashMap<>();
            queryMap.put("albumId", String.valueOf(album.getId()));
            String photoUrl = MappingUtil.buildUrl(scheme, host, photoPath, queryMap);
            log.info("the photo url is {}", photoUrl);
            ResponseEntity<List<Photo>> photoListResponseEntity = restTemplate
                .exchange(photoUrl, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Photo>>() {
                    });
            log.info("response recieved is {}", photoListResponseEntity.getBody());

            for (Photo photo : photoListResponseEntity.getBody()) {
                photo.setAlbum(album);
                photos.add(photo);
            }
        }

        return photoRepository.saveAll(photos);
    }
}
