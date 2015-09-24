package com.sebprunier.nantes_quizz.district;

public class DistrictQuizz {

    private String districtInfoLat;
    private String districtInfoLon;
    private String id1;
    private String nom1;
    private String id2;
    private String nom2;
    private String id3;
    private String nom3;

    public DistrictQuizz(String districtInfoLat, String districtInfoLon, String id1, String nom1, String id2, String nom2, String id3, String nom3) {
        this.districtInfoLat = districtInfoLat;
        this.districtInfoLon = districtInfoLon;
        this.id1 = id1;
        this.nom1 = nom1;
        this.id2 = id2;
        this.nom2 = nom2;
        this.id3 = id3;
        this.nom3 = nom3;
    }

    public String getDistrictInfoLat() {
        return districtInfoLat;
    }

    public String getDistrictInfoLon() {
        return districtInfoLon;
    }

    public String getId1() {
        return id1;
    }

    public String getNom1() {
        return nom1;
    }

    public String getId2() {
        return id2;
    }

    public String getNom2() {
        return nom2;
    }

    public String getId3() {
        return id3;
    }

    public String getNom3() {
        return nom3;
    }
}
