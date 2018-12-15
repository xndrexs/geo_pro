package de.hsrm.mi.devops04.GeoPro;

import lombok.extern.slf4j.Slf4j;
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

    private final String API = "http://api.geoiplookup.net/?query=";

    public GeoInfo getGeoInfo(String ip) {

        URI url = URI.create(API + ip);

        RestTemplate resttemplate = new RestTemplate();

        ResponseEntity<GeoInfo> response = resttemplate.exchange(
                RequestEntity
                        .get(url)
                        .accept(MediaType.APPLICATION_XML)
                        .build(),
                GeoInfo.class
        );
        return response.getBody();
    }
}
