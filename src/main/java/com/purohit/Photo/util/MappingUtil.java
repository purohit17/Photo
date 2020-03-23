package com.purohit.Photo.util;

import java.util.Map;
import java.util.UUID;
import org.apache.commons.collections4.MapUtils;
import org.apache.http.client.utils.URIBuilder;

public class MappingUtil {

    public static String buildUrl(String scheme,String host,String path, Map<String,String> queryParams) throws Exception{
        URIBuilder uriBuilder = getUriBuilder();
        uriBuilder.setScheme(scheme)
            .setHost(host)
            .setPath(path)
            .build();
        if(MapUtils.isNotEmpty(queryParams)){
            queryParams.forEach((param,value)->uriBuilder.setParameter(param,value));
        }
        return uriBuilder.toString();
    }

    public static URIBuilder getUriBuilder(){
        return new URIBuilder();
    }

    public static String generateUUID(){
        return UUID.randomUUID().toString();
    }
}
