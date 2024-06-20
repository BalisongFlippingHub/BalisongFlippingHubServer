package com.example.BalisongFlipping.services;

import com.example.BalisongFlipping.dtos.FileDto;
import com.example.BalisongFlipping.repositories.AccountRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class JavaFSService {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private GridFsOperations gridFsOperations;

    public JavaFSService(GridFsOperations gridFsOperations, GridFsTemplate gridFsTemplate) {
        this.gridFsTemplate = gridFsTemplate;
        this.gridFsOperations = gridFsOperations;
    }

    public String addAsset(String title, MultipartFile file) throws IOException {
        System.out.println("Adding asset");
        DBObject metadata = new BasicDBObject();

        metadata.put("type", "file");
        metadata.put("title", title);
        ObjectId id = gridFsTemplate.store(file.getInputStream(), file.getName(), file.getContentType(), metadata);


        return id.toString();
    }

    public FileDto getAsset(String id) throws Exception {
        GridFSFile file = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));

        if (file == null) {
            return null;
        }

        FileDto assetDto = new FileDto(
                file.getMetadata().get("title").toString(),
                gridFsOperations.getResource(file).getContentType(),
                file.getMetadata().get("fileSize").toString(),
                (gridFsOperations.getResource(file).getInputStream()).readAllBytes()
        );

        return assetDto;
    }
}
