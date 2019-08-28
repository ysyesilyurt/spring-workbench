package com.ysyesilyurt.Service;

import com.ysyesilyurt.EntityModel.PostEntityModel;
import com.ysyesilyurt.Exception.ResourceNotFoundException;
import com.ysyesilyurt.Repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private PostRepository postRepository;

    /*
    *   Injection through Constructor
    * */
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Page<PostEntityModel> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public PostEntityModel getPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("PostId %ld not found.", postId)));
    }

    public PostEntityModel getPostByTitle(String title) {
        // Since titles are unique this method will only return 1 object or throw RNFE
        return postRepository.findByTitle(title)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Post %s not found.", title)));
    }

    public Page<PostEntityModel> getPostsByCategory(String category, Pageable pageable) {
        // Since same categories can be assigned to multiple posts this method may return 1 or more object
        return postRepository.findByCategory(category, pageable);
    }

    public PostEntityModel createPost(PostEntityModel post) {
        return postRepository.save(post);
    }

    public PostEntityModel updatePost(Long postId, PostEntityModel postUpdatedCredentials) {
        /* We simply retrieve the post if exists (or else we throw our own not found exception) and update it
           inside Optional.map with a dummy instance "postEntityModel", then we save it and return the updated object
         * */
        return postRepository.findById(postId).map(postEntityModel -> {
            // User can not update post's id
            postEntityModel.setTitle(postUpdatedCredentials.getTitle());
            postEntityModel.setDescription(postUpdatedCredentials.getDescription());
            postEntityModel.setContent(postUpdatedCredentials.getContent());
            postEntityModel.setCategory(postUpdatedCredentials.getCategory());
            return postRepository.save(postEntityModel);
        }).orElseThrow(() -> new ResourceNotFoundException(String.format("PostId %ld not found, " +
                "could not update Post", postId)));
    }

    public ResponseEntity<?> deletePost(Long postId) {
        return postRepository.findById(postId).map(postEntityModel -> {
            postRepository.delete(postEntityModel);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(String.format("PostId %ld not found, " +
                "could not delete Post", postId)));

        // TODO: try ifPresent
    }
}
