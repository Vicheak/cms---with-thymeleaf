package com.vicheak.cms.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    String uploadThumbnail(MultipartFile file);

}
