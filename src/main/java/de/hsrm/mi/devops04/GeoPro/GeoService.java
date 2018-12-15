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

    private final GeoCache geoCache;
    private final String API = "http://api.geoiplookup.net/?query=";

    public GeoService(GeoCache geoCache) {
        this.geoCache = geoCache;
    }

    public GeoInfo getGeoInfo(String ip) {
        GeoInfo cachedGeoInfo = geoCache.getGeoInfoFromCache(ip);
        if (cachedGeoInfo != null) {
            return cachedGeoInfo;
        } else {
            ResponseEntity<GeoInfo> response = getResponse(ip);
            log.info("New external response for {}", ip);
            GeoInfo newGeoInfo = response.getBody();
            geoCache.addGeoInfoToCache(newGeoInfo);
            return newGeoInfo;
        }
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
