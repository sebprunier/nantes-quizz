package com.sebprunier.nantes_quizz.photos;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import javax.inject.Singleton;
import java.security.MessageDigest;
import java.util.*;

@Singleton
public class PhotosRepository {

    private Map<String, PhotoInfo> PHOTOS_INFOS = new HashMap<>();

    public PhotosRepository() throws Exception {
        for (String photoId : PHOTOS) {
            PhotoInfo photoInfo = new PhotoInfo(photoId);
            Metadata metadata = ImageMetadataReader.readMetadata(getClass().getResourceAsStream("/app/img/photos/" + photoId));
            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    if ("Copyright Notice".equals(tag.getTagName())) {
                        photoInfo.setCredit(tag.getDescription().split("\n")[0].trim());
                    }
                    if ("Object Name".equals(tag.getTagName())) {
                        photoInfo.setName(tag.getDescription());
                    }
                    if ("Caption/Abstract".equals(tag.getTagName())) {
                        photoInfo.setDetails(tag.getDescription().split("\n")[0].trim());
                    }
                }
            }

            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(photoInfo.getDetails().getBytes());
            byte bytes[] = messageDigest.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte b : bytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            photoInfo.setHash(hexString.toString());

            PHOTOS_INFOS.put(photoId, photoInfo);
        }
    }

    public List<PhotoInfo> random3() {
        List<String> photosId = Arrays.asList(PHOTOS);
        Collections.shuffle(photosId);
        return Arrays.asList(
                PHOTOS_INFOS.get(photosId.get(0)),
                PHOTOS_INFOS.get(photosId.get(1)),
                PHOTOS_INFOS.get(photosId.get(2))
        );
    }

    public PhotoInfo getById(String id) {
        return PHOTOS_INFOS.get(id);
    }

    private static final String[] PHOTOS = new String[]{
            "photo_Ville-de-Nantes_00014099.jpg",
            "photo_Ville-de-Nantes_00015851.jpg",
            "photo_Ville-de-Nantes_00016537.jpg",
            "photo_Ville-de-Nantes_00017995.jpg",
            "photo_Ville-de-Nantes_00029026.jpg",
            "photo_Ville-de-Nantes_00029763.jpg",
            "photo_Ville-de-Nantes_00032633.jpg",
            "photo_Ville-de-Nantes_VDN00035001_11_11.jpg",
            "photo_Ville-de-Nantes_VDN00035001_17_18.jpg",
            "photo_Ville-de-Nantes_VDN00035001_20_7.jpg",
            "photo_Ville-de-Nantes_VDN00035001_33_9.jpg",
            "photo_Ville-de-Nantes_VDN00035371.jpg",
            "photo_Ville-de-Nantes_VDN00035390.jpg",
            "photo_Ville-de-Nantes_VDN00036343.jpg",
            "photo_Ville-de-Nantes_VDN00041665.jpg",
            "photo_Ville-de-Nantes_VDN00042020.jpg",
            "photo_Ville-de-Nantes_VDN00043671.jpg",
            "photo_Ville-de-Nantes_VDN00045344.jpg",
            "photo_Ville-de-Nantes_VDN00045399.jpg",
            "photo_Ville-de-Nantes_VDN00045746.jpg",
            "photo_Ville-de-Nantes_VDN00046158.jpg",
            "photo_Ville-de-Nantes_VDN00046793.jpg",
            "photo_Ville-de-Nantes_VDN00049650.jpg",
            "photo_Ville-de-Nantes_VDN00050520.jpg",
            "photo_Ville-de-Nantes_VDN00051072.jpg",
            "photo_Ville-de-Nantes_VDN00052436.jpg",
            "photo_Ville-de-Nantes_VDN00056298.jpg",
            "photo_Ville-de-Nantes_VDN00057534.jpg",
            "photo_Ville-de-Nantes_VDN00057757.jpg",
            "photo_Ville-de-Nantes_VDN00058112.jpg",
            "photo_Ville-de-Nantes_VDN00078931.jpg",
            "photo_Ville-de-Nantes_VDN00079393.jpg",
            "photo_Ville-de-Nantes_VDN00079544.jpg",
            "photo_Ville-de-Nantes_VDN00080616.jpg",
            "RR-VDN00084153.jpg",
            "VDN00056731.jpg"
    };

}
