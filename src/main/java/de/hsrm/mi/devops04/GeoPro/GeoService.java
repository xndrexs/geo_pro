package de.hsrm.mi.devops04.GeoPro;

import org.springframework.stereotype.Service;

@Service
public class GeoService {

    public GeoInfo getGeoInfo(String ip) {
        GeoInfo geoInfo = new GeoInfo();
        return geoInfo;
    }
}
