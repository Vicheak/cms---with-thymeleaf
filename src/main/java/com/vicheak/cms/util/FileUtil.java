package com.vicheak.cms.util;

public class FileUtil {

    public static String getExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf(".");
        return fileName.substring(lastDotIndex + 1);
    }

}
