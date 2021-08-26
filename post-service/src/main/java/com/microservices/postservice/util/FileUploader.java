package com.microservices.postservice.util;

import com.microservices.postservice.entity.ImageEntity;
import com.microservices.postservice.entity.PostEntity;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.io.FilenameUtils;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class FileUploader {
    private static final String basePath = "/home/biuea/Desktop/MyRentalPlatform/post-service/src/main/resources";

    public static List<ImageEntity> parseFileInfo(
        List<MultipartFile> images,
        PostEntity post
    ) throws Exception {
        if(CollectionUtils.isEmpty(images)) {
            return Collections.emptyList();
        }

        String savePath = Paths.get(basePath, "upload").toString();

        if(!new File(savePath).exists()) {
            try {
                new File(savePath).mkdir();
            } catch(Exception e) {
                e.getStackTrace();
            }
        }

        List<ImageEntity> imageList = new ArrayList<>();

        for(MultipartFile image : images) {
            String orgFilename = image.getOriginalFilename();

            if(orgFilename == null || "".equals(orgFilename)) {
                continue;
            }

            String fileName = MD5Generator(FilenameUtils.getBaseName(orgFilename)).toString() + "." + FilenameUtils.getExtension(orgFilename);
            String filePath = Paths.get(savePath, fileName).toString();

            ImageEntity temp_image = ImageEntity.builder()
                                                .post(post)
                                                .orgFilename(orgFilename)
                                                .fileName(fileName)
                                                .filePath(filePath)
                                                .fileSize(image.getSize())
                                                .build();

            imageList.add(temp_image);

            try {
                File file = new File(filePath);

                image.transferTo(file);

                file.setWritable(true);
                file.setReadable(true);
            } catch(IOException e) {
                throw new FileUploadException("[" + image.getOriginalFilename() + "] failed to save file...");
            } catch(Exception e) {
                throw new FileUploadException("[" + image.getOriginalFilename() + "] failed to save file...");
            }
        }

        return imageList;
    }

    public static String MD5Generator(String input) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest mdMD5 = MessageDigest.getInstance("MD5");

        mdMD5.update(input.getBytes("UTF-8"));

        byte[] md5Hash = mdMD5.digest();
        StringBuilder hexMD5hash = new StringBuilder();

        for(byte b : md5Hash) {
            String hexString = String.format("%02x", b);

            hexMD5hash.append(hexString);
        }

        return hexMD5hash.toString();
    }
}