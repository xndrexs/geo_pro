package de.hsrm.mi.devops04.GeoPro;

import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement(name = "geoinfo")
public class GeoInfo {
    String ip;
    String country;
    String town;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
