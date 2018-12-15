package de.hsrm.mi.devops04.GeoPro;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "cache")
@Validated
public class GeoConfig {

    @Min(5)
    private Long maxAge;

    @Max(50)
    private int maxEntries;

    private List<String> countrycodes = new ArrayList<>();
}
