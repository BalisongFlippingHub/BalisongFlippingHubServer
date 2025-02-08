package com.example.BalisongFlipping.services;


import com.example.BalisongFlipping.dtos.postsDtos.CollectionTimelineDto;
import com.example.BalisongFlipping.enums.posts.PostTypes;

import com.example.BalisongFlipping.enums.posts.tags.CollectionTimelineTags;
import com.example.BalisongFlipping.modals.collectionKnives.CollectionKnife;
import com.example.BalisongFlipping.modals.posts.CollectionTimelinePost;
import com.example.BalisongFlipping.modals.posts.PostWrapper;

import com.example.BalisongFlipping.repositories.PostsRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class PostService {

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private JavaFSService javaFSService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private CollectionKnifeService collectionKnifeService;

    private List<CollectionTimelineDto> convertCollectionTimelinePostToDto (List<PostWrapper> posts) throws Exception {
        List<CollectionTimelineDto> timelinePosts = new ArrayList<>();
        for (PostWrapper post: posts) {
            CollectionTimelinePost casedPost = (CollectionTimelinePost) post;
            timelinePosts.add(new CollectionTimelineDto(
                    post.getId(),
                    post.getAccountId(),
                    post.getCreationDate().toString(),
                    collectionKnifeService.getCollectionKnifeCoverPhoto(((CollectionTimelinePost) post).getCollectionKnife()),
                    ((CollectionTimelinePost) post).getFiles(),
                    post.getPostType(),
                    ((CollectionTimelinePost) post).getPostTags(),
                    collectionKnifeService.getCollectionKnifeDisplayName(((CollectionTimelinePost) post).getCollectionKnife())
            ));
        }

        return timelinePosts;
    }

    public CollectionTimelinePost createAddKnifeCollectionTimelinePost(String accountId, CollectionKnife newKnife, List<String> fileIds) throws Exception {
        // create new post object and set attributes
        CollectionTimelinePost newTimelinePost = new CollectionTimelinePost();

        newTimelinePost.setAccountId(accountId);
        newTimelinePost.setCollectionKnife(newKnife.getId());
        newTimelinePost.setFiles(fileIds);

        newTimelinePost.setPostType(PostTypes.COLLECTION_TIMELINE);
        newTimelinePost.addPostTag(CollectionTimelineTags.NEW_KNIFE);

        if (newKnife.isFavoriteKnife()) newTimelinePost.addPostTag(CollectionTimelineTags.GRAIL);

        if (newKnife.isFavoriteFlipper()) newTimelinePost.addPostTag(CollectionTimelineTags.FAV_FLIPPER);

        newTimelinePost.setCreationDate(new Date());

        // save in DB
        return postsRepository.save(newTimelinePost);
    }

    public List<CollectionTimelineDto> getCollectionTimelinePosts(String accountId) throws Exception {
        Query query = new Query();
        query.addCriteria(new Criteria().andOperator(Criteria.where("accountId").is(accountId),Criteria.where("postType").is(PostTypes.COLLECTION_TIMELINE)));
        query.with(Sort.by(Sort.Direction.DESC, "creationDate"));

        return convertCollectionTimelinePostToDto(mongoTemplate.find(query, PostWrapper.class));
    }
}
