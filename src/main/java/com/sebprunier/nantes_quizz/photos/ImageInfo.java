package com.sebprunier.nantes_quizz.photos;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

public class ImageInfo {


    public static void main(String[] args) throws Exception {
        Metadata metadata = ImageMetadataReader.readMetadata(ImageInfo.class.getResourceAsStream("/photos/RR-VDN00084153.jpg"));
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                if ("Copyright Notice".equals(tag.getTagName())) {
                    System.out.println(tag.getDescription());
                }
                if ("Caption/Abstract".equals(tag.getTagName())) {
                    System.out.println(tag.getDescription());
                }
                if ("Object Name".equals(tag.getTagName())) {
                    System.out.println(tag.getDescription());
                }
                //System.out.println(tag);
            }
        }
    }

}
