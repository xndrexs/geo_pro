package de.hsrm.mi.devops04.GeoPro;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GeoCache {

    List<GeoInfo> cache;

    @Value("${maxentries:10}")
    private int maxEntries;

    @Value("${maxage:60}")
    private int maxAge;


}
