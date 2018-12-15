package de.hsrm.mi.devops04.GeoPro;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/geoinfo")
@RestController
public class GeoController {

    private GeoService geoService;

    public GeoController(GeoService geoService) {
        this.geoService = geoService;
    }

    @GetMapping(value = "/{ip}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GeoInfo getGeoInfo(@PathVariable String ip) {
        return this.geoService.getGeoInfo(ip);
    }
}