package de.hsrm.mi.devops04.GeoPro;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@Validated
@Component
@Configuration
@ConfigurationProperties(prefix = "cache")
public class GeoConfig {

    public Long getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Long maxAge) {
        this.maxAge = maxAge;
    }

    public int getMaxEntries() {
        return maxEntries;
    }

    public void setMaxEntries(int maxEntries) {
        this.maxEntries = maxEntries;
    }

    public List<String> getCountrycodes() {
        return countrycodes;
    }

    public void setCountrycodes(List<String> countrycodes) {
        this.countrycodes = countrycodes;
    }

    @Min(5)
    private Long maxAge;

    @Max(50)
    private int maxEntries;

    private List<String> countrycodes = new ArrayList<>();
}
