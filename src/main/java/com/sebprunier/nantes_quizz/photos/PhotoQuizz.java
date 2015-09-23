package com.sebprunier.nantes_quizz.photos;

public class PhotoQuizz {

    private String photoId;

    private String photoCredit;

    private String option1Hash;
    private String option1;

    private String option2Hash;
    private String option2;

    private String option3Hash;
    private String option3;

    public PhotoQuizz(String photoId, String photoCredit, String option1Hash, String option1, String option2Hash, String option2, String option3Hash, String option3) {
        this.photoId = photoId;
        this.photoCredit = photoCredit;
        this.option1Hash = option1Hash;
        this.option1 = option1;
        this.option2Hash = option2Hash;
        this.option2 = option2;
        this.option3Hash = option3Hash;
        this.option3 = option3;
    }

    public String getPhotoId() {
        return photoId;
    }

    public String getPhotoCredit() {
        return photoCredit;
    }

    public String getOption1Hash() {
        return option1Hash;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2Hash() {
        return option2Hash;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3Hash() {
        return option3Hash;
    }

    public String getOption3() {
        return option3;
    }
}
