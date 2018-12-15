package de.hsrm.mi.devops04.GeoPro;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.net.URI;

@Slf4j
@Service
public class GeoService implements Serializable {

    @Autowired
    private GeoCache geoCache;

    private final String API = "http://api.geoiplookup.net/?query=";

    public GeoInfo getGeoInfo(String ip) {
        ResponseEntity<GeoInfo> response = getResponse(ip);
        return response.getBody();
    }

    private ResponseEntity<GeoInfo> getResponse(String ip) {
        RestTemplate resttemplate = new RestTemplate();
        return resttemplate.exchange(
                RequestEntity
                        .get(generateUrl(ip))
                        .accept(MediaType.APPLICATION_XML)
                        .build(),
                GeoInfo.class
        );
    }

    private URI generateUrl(String ip) {
        return URI.create(API + ip);
    }
}
