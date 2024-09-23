package com.shoppingShots.shoppingShots.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class CommonUtils {
    private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);

    public static String checkForImageSave(MultipartFile file,String refPath,String oldImageName) throws IOException {
        String fileName = !file.isEmpty()?file.getOriginalFilename():null;
        File saveFile = new ClassPathResource("public/img").getFile();
        Path path = null;
        if((fileName != null && !fileName.equalsIgnoreCase(oldImageName))){
            try{
                path = Paths.get(saveFile.getAbsoluteFile()+File.separator+refPath+File.separator+fileName);
                logger.info(String.valueOf(path));
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            }
            catch(IOException ioException){
                logger.info(ioException.getMessage());
                ioException.printStackTrace();
            }
            catch(Exception exception){
                logger.info(exception.getMessage());
                exception.printStackTrace();
            }

        }
        return fileName;
    }
}
