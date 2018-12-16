package de.hsrm.mi.devops04.GeoPro;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class GeoHealth implements HealthIndicator {

    private final GeoCache geoCache;

    public GeoHealth(GeoCache geoCache) {
        this.geoCache = geoCache;
    }

    @Override
    public Health health() {
        return Health.up()
                .withDetail("cacheSize", geoCache.getMaxEntries())
                .withDetail("cacheUsed", geoCache.getCache().size())
                .withDetail("maxAge", geoCache.getMaxAge())
                .withDetail("oldestEntry", geoCache.getOldestEntryFromCache())
                .withDetail("newestEntry", geoCache.getNewestEntryFromCache())
                .build();
    }
}
