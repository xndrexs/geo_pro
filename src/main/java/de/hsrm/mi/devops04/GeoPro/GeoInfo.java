package de.hsrm.mi.devops04.GeoPro;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Data
@XmlRootElement(name = "ip")
public class GeoInfo {

    @Data
    public class GeoInfoResults {

        @Data
        public class GeoInfoResult {
            private String ip;
            private String isp;
            private String city;
            private String countrycode;
            private String countryname;
        }

        private GeoInfoResult result;

    }

    private GeoInfoResults results;
    private Long timeStamp = new Date().getTime();
}
