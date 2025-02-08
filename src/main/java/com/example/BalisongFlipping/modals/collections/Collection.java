package com.example.BalisongFlipping.modals.collections;

import com.example.BalisongFlipping.modals.collectionKnives.CollectionKnife;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("collections")
public class Collection {

    public Collection(String userId) {
        this.userId = userId;
        bannerImg = "";
        collectedKnives = new ArrayList<>();
    }

    @Id
    private String id;

    @Indexed(unique = true)
    private String userId;

    private String bannerImg;

    private List<String> collectedKnives;
}
