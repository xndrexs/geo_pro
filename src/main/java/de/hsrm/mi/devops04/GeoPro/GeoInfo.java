package de.hsrm.mi.devops04.GeoPro;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ip")
public class GeoInfo {

    public class GeoInfoResults {

        public class GeoInfoResult {
            private String ip;
            private String isp;
            private String city;
            private String countrycode;
            private String countryname;

            public String getIp() {
                return ip;
            }

            public void setIp(String ip) {
                this.ip = ip;
            }

            public String getIsp() {
                return isp;
            }

            public void setIsp(String isp) {
                this.isp = isp;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCountrycode() {
                return countrycode;
            }

            public void setCountrycode(String countrycode) {
                this.countrycode = countrycode;
            }

            public String getCountryname() {
                return countryname;
            }

            public void setCountryname(String countryname) {
                this.countryname = countryname;
            }
        }

        public GeoInfoResult getResult() {
            return result;
        }

        public void setResult(GeoInfoResult result) {
            this.result = result;
        }

        private GeoInfoResult result;

    }

    public GeoInfoResults getResults() {
        return results;
    }

    public void setResults(GeoInfoResults results) {
        this.results = results;
    }

    private GeoInfoResults results;
}
