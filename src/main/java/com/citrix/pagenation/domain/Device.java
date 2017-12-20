package com.citrix.pagenation.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "devices")
public class Device {
    private int sno;
    @Id
    private String id;
    private String imei;
    private String platform;
    private boolean byod;
    private String version;
    private String userId;

    public Device(int sno, String id, String imei, String platform, boolean byod, String version, String userId) {
        this.sno = sno;
        this.id = id;
        this.imei = imei;
        this.platform = platform;
        this.byod = byod;
        this.version = version;
        this.userId = userId;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isByod() {
        return byod;
    }

    public void setByod(boolean byod) {
        this.byod = byod;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Device{" +
                "sno=" + sno +
                ", id='" + id + '\'' +
                ", imei='" + imei + '\'' +
                ", platform='" + platform + '\'' +
                ", byod=" + byod +
                ", version='" + version + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
