package com.vicheak.cms.service.impl;

import com.vicheak.cms.service.FileService;
import com.vicheak.cms.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Value("${file.server-path}")
    private String serverPath;

    @Override
    public String uploadThumbnail(MultipartFile file) {
        String extension = FileUtil.getExtension(Objects.requireNonNull(file.getOriginalFilename()));
        if (extension.equals("png") ||
                extension.equals("jpg") ||
                extension.equals("webp") ||
                extension.equals("jpeg")) {
            //generate unique name with the file extension
            String name = UUID.randomUUID().toString() + "." + extension;

            //create absolute path
            Path path = Paths.get(serverPath + name);

            try {
                Files.copy(file.getInputStream(), path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return name;
        }

        return null;
    }
}
