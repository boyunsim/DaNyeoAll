package com.green.danyeoall.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
public class MyFileUtils {
    private final String uploadPath;

    public String getUploadPath() {
        return uploadPath;
    }

    public MyFileUtils(@Value("${file.directory}") String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public String makeFolders(String path) {
        File file = new File(uploadPath, path);
        if(!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    public String getExt(String originalFileName) {
        int lastIdx = originalFileName.lastIndexOf(".");
        return originalFileName.substring(lastIdx);
    }

    public String makeRandomFileName() {
        return UUID.randomUUID().toString();
    }

    public String makeRandomFileName(String file) {
        return makeRandomFileName() + getExt(file);
    }

    public String makeRandomFileName(MultipartFile multipartFile) {
        return makeRandomFileName(multipartFile.getOriginalFilename());
    }

    public void transferTo(MultipartFile multipartFile, String path) throws IOException {
        File file = new File(uploadPath, path);
        multipartFile.transferTo(file);
    }

    //폴더 삭제, e.g "user/1"
    public void deleteFolder(String path, boolean deleteRootFolder) {
        File folder = new File(path);
        if(folder.exists() && folder.isDirectory()) { //폴더가 존재하면서 디렉토리인가?
            File[] includedFiles = folder.listFiles();

            for(File f : includedFiles) {
                if(f.isDirectory()) {
                    deleteFolder(f.getAbsolutePath(), true);
                } else {
                    f.delete();
                }
            }
            if(deleteRootFolder) {
                folder.delete();
            }
        }
    }
}
