package com.ysyesilyurt.Service;

import com.ysyesilyurt.EntityModel.PostEntityModel;
import com.ysyesilyurt.Exception.ResourceNotFoundException;
import com.ysyesilyurt.Repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PostService {

    private PostRepository postRepository;

    /*
    *   Injection through Constructor
    * */
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Page<PostEntityModel> getAllPosts(String title, String category, Pageable pageable) {
        /* As you can see below code is just nasty ifology and creates method explosion in the repository since the
        *  Optionality of Request Parameters
        *
        *  To address to this issue we will introduce search API with Spring Data and Specification Argument Resolver
        *  in the next spring-workbench (spring-workbench-3)
        *  */

        if (title != null && category != null)
            return postRepository.findByTitleAndCategory(title, category, pageable);
        else if (category != null)
            return postRepository.findByCategory(category, pageable);
        else if (title != null)
            return postRepository.findByTitle(title, pageable);
        else
            return postRepository.findAll(pageable);
    }

    public PostEntityModel getPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("PostId %d not found.", postId)));
    }

    public PostEntityModel createPost(PostEntityModel post) {
        return this.persistPost(post);
    }

    public PostEntityModel updatePost(Long postId, PostEntityModel postUpdatedCredentials) {
        /* We simply retrieve the post if exists (or else we throw our own not found exception) and update it
           inside Optional.map with a dummy instance "postEntityModel", then we save it and return the updated object
         * */
        return postRepository.findById(postId).map(postEntityModel -> {
            // User can not update post's id
            if (postUpdatedCredentials.getTitle() != null)
                postEntityModel.setTitle(postUpdatedCredentials.getTitle());
            if (postUpdatedCredentials.getDescription() != null)
                postEntityModel.setDescription(postUpdatedCredentials.getDescription());
            if (postUpdatedCredentials.getContent() != null)
                postEntityModel.setContent(postUpdatedCredentials.getContent());
            if (postUpdatedCredentials.getCategory() != null)
                postEntityModel.setCategory(postUpdatedCredentials.getCategory());
            return this.persistPost(postEntityModel);
        }).orElseThrow(() -> new ResourceNotFoundException(String.format("PostId %d not found, " +
                "could not update Post", postId)));
    }

    @Transactional
    public ResponseEntity<?> deletePost(Long postId) {
        return postRepository.findById(postId).map(postEntityModel -> {
            postRepository.delete(postEntityModel);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(String.format("PostId %d not found, " +
                "could not delete Post", postId)));

        // TODO: try ifPresent
    }

    @Transactional
    private PostEntityModel persistPost(PostEntityModel post) {
        return postRepository.save(post);
    }
}
