package com.example.BalisongFlipping.services;

import com.example.BalisongFlipping.dtos.FileDto;
import org.springframework.stereotype.Service;

@Service
public class ImgAndVideoService {
    private static  JavaFSService javaFSService;

   public ImgAndVideoService(JavaFSService javaFSService1) {
       this.javaFSService = javaFSService1;
   }

    public FileDto getFile(String id) throws Exception {
        return javaFSService.getAsset(id);
    }
}
