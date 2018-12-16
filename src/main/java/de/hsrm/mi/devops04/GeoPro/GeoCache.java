package de.hsrm.mi.devops04.GeoPro;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Data
@Component
public class GeoCache {

    private final GeoConfig geoConfig;

    private List<GeoInfo> cache;
    private List<String> countrycodes;
    private int maxEntries;
    private Long maxAge;

    @Autowired
    public GeoCache(GeoConfig geoConfig) {
        this.geoConfig = geoConfig;

        cache = new ArrayList<>();
        maxEntries = geoConfig.getMaxEntries();
        countrycodes = geoConfig.getCountrycodes();
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
            log.info("Entry not added, found in non-cache");
            return;
        }
        if (cache.size() >= maxEntries) {
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
        cache.removeIf(geoInfo -> timeExceeded(geoInfo));
        log.info("Old entries removed from cache, current size {}", cache.size());
    }

    private boolean timeExceeded(GeoInfo geoInfo) {
        Long currentTime = new Date().getTime();
        Long timePassed = (currentTime - geoInfo.getTimeStamp()) / 1000;
        return timePassed > maxAge;
    }

    private void removeOldestEntry() {
        GeoInfo geoInfo = getOldestEntryFromCache();
        cache.remove(geoInfo);
        log.info("Oldest entry was removed from cache");
    }

    public GeoInfo getOldestEntryFromCache() {
        //TODO: Fix this
        GeoInfo geoInfo;
        try {
            geoInfo = Collections.min(cache);
        } catch (NoSuchElementException e) {
            geoInfo = new GeoInfo();
        }
        return geoInfo;
    }

    public GeoInfo getNewestEntryFromCache() {
        //TODO: Fix this
        GeoInfo geoInfo;
        try {
            geoInfo = Collections.max(cache);
        } catch (NoSuchElementException e) {
            geoInfo = new GeoInfo();
        }
        return geoInfo;
    }
}
