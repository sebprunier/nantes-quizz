package com.sebprunier.nantes_quizz.photos;

public class PhotoInfo {

    private String id;

    private String name;

    private String details;

    private String credit;

    private String hash;

    public PhotoInfo(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public String getCredit() {
        return credit;
    }

    public String getHash() {
        return hash;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        return "[" + id + "] - " + name + " (" + credit + ")";
    }
}
