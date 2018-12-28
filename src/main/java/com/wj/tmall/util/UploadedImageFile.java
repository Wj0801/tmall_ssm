package com.wj.tmall.util;

import org.springframework.web.multipart.MultipartFile;

public class UploadedImageFile {
    MultipartFile image;
      //用于接受上传文件的注入
    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
