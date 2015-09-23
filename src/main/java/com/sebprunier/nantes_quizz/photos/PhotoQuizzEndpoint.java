package com.sebprunier.nantes_quizz.photos;

import net.codestory.http.annotations.Get;
import net.codestory.http.annotations.Post;
import net.codestory.http.annotations.Prefix;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@Prefix("api/quizz/photo")
public class PhotoQuizzEndpoint {

    private PhotosRepository photosRepository;

    @Inject
    public PhotoQuizzEndpoint(PhotosRepository photosRepository) {
        this.photosRepository = photosRepository;
    }

    @Get("new")
    public PhotoQuizz newPhotoQuizz() {
        List<PhotoInfo> photoInfos = photosRepository.random3();

        PhotoInfo photoInfo = photoInfos.get(0);
        String photoId = photoInfo.getId();
        String photoCredit = photoInfo.getCredit();

        Collections.shuffle(photoInfos);

        return new PhotoQuizz(
                photoId,
                photoCredit,
                photoInfos.get(0).getHash(),
                photoInfos.get(0).getDetails(),
                photoInfos.get(1).getHash(),
                photoInfos.get(1).getDetails(),
                photoInfos.get(2).getHash(),
                photoInfos.get(2).getDetails()
        );
    }

    @Post("check")
    public PhotoQuizzResult checkPhotoQuizz(PhotoQuizzProposal photoQuizzProposal) {
        PhotoInfo photoInfo = photosRepository.getById(photoQuizzProposal.getPhotoId());
        PhotoQuizzResult result = new PhotoQuizzResult();
        result.setResult(photoInfo.getHash().equals(photoQuizzProposal.getProposal()));
        result.setCorrectOption(photoInfo.getDetails());
        return result;
    }
}
