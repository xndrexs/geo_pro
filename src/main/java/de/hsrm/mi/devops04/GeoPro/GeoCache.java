package de.hsrm.mi.devops04.GeoPro;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class GeoCache {

    private List<GeoInfo> cache = new ArrayList<>();

    @Value("${cache.maxentries:10}")
    private int maxEntries;

    @Value("${cache.maxage:60}")
    private Long maxAge;

    public GeoInfo getGeoInfoFromCache(String ip) {
        removeOldEntries();
        for (GeoInfo geoInfo : cache) {
            String geoInfoIp = geoInfo.getResults().getResult().getIp();
            if (geoInfoIp.equals(ip)) {
                log.info("Return entry from cache with ip {}", ip);
                return geoInfo;
            }
        }
        return null;
    }

    public void addGeoInfoToCache(GeoInfo geoInfo) {
        if (cache.size() > maxEntries) {
            removeOldestEntry();
        }
        cache.add(geoInfo);
        log.info("New entry added to cache with ip {}", geoInfo.getResults().getResult().getIp());
    }

    public void removeOldEntries() {
        cache.removeIf((geoInfo) ->
            timeExceeded(geoInfo)
        );
        log.info("Old entries removed from cache");
    }

    private boolean timeExceeded(GeoInfo geoInfo) {
        Long currentTime = new Date().getTime();
        Long timePassed = (currentTime - geoInfo.getTimeStamp()) / 1000;
        return timePassed > maxAge;
    }

    private void removeOldestEntry() {
        GeoInfo geoInfo = Collections.min(cache, Comparator.comparing(g -> g.getTimeStamp()));
        cache.remove(geoInfo);
        log.info("Oldest entry was removed from cache");
    }
}
