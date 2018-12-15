package de.hsrm.mi.devops04.GeoPro;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class GeoCache {

    @Autowired
    GeoConfig geoConfig;

    private List<GeoInfo> cache;
    private List<String> countrycodes;
    private int maxEntries;
    private Long maxAge;

    public GeoCache() {
        cache = new ArrayList<>();
        countrycodes = geoConfig.getCountrycodes();
        maxEntries = geoConfig.getMaxEntries();
        maxAge = geoConfig.getMaxAge();
    }

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
        if (checkCountryCode(geoInfo)) {
            log.info("Entry not added, found in non-cached");
            return;
        }
        if (cache.size() > maxEntries) {
            removeOldestEntry();
        }
        cache.add(geoInfo);
        log.info("New entry added to cache with ip {}", geoInfo.getResults().getResult().getIp());
    }

    private boolean checkCountryCode(GeoInfo geoInfo) {
        for (String countrycode : countrycodes) {
            String geoInfoCountryCode = geoInfo.getResults().getResult().getCountrycode();
            if (countrycode.equals(geoInfoCountryCode)) {
                return true;
            }
        }
        return false;
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
