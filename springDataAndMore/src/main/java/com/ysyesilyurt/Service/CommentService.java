package com.ysyesilyurt.Service;

import com.ysyesilyurt.EntityModel.CommentEntityModel;
import com.ysyesilyurt.Exception.ResourceNotFoundException;
import com.ysyesilyurt.Repository.CommentRepository;
import com.ysyesilyurt.Repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    /*
     *   Injection through Constructor
     * */
    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public Page<CommentEntityModel> getAllCommentsByPostId(Long postId, Pageable pageable) {
        /* Returns all comments of a post */
        if (!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException(String.format("PostId %d not found, " +
                    "could not retrieve Comments under this post.", postId));
        }
        else
            return commentRepository.findByPostId(postId, pageable);
    }

    public Optional<CommentEntityModel> getCommentById(Long commentId) {
        return commentRepository.findById(commentId);
    }

    public CommentEntityModel createComment(Long postId, CommentEntityModel newComment) {
        return postRepository.findById(postId).map(postEntityModel -> {
            newComment.setPost(postEntityModel);
            return this.persistComment(newComment);
        }).orElseThrow(() -> new ResourceNotFoundException(String.format("PostId %d not found, " +
                "could not create Comment under this post.", postId)));
    }

    public CommentEntityModel updateComment(Long postId, Long commentId, CommentEntityModel commentUpdatedCredentials) {
        /* User can change everything in comment except its Post */

        if (!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException(String.format("PostId %d not found, " +
                    "could not update Comment under this post.", postId));
        }
        else {
            /* First way to do it */
            return commentRepository.findById(commentId).map(commentToUpdate -> {
                // User can not update comment's id and post
                commentToUpdate.setText(commentUpdatedCredentials.getText());
                return persistComment(commentToUpdate);
            }).orElseThrow(() -> new ResourceNotFoundException(String.format("Comment not found with id %d, " +
                    "could not update Comment under this post.", commentId)));
            /* Alternative way */
//          return commentRepository.findByIdAndPostId(commentId, postId).map(commentEntityModel -> {
//                commentEntityModel.setText(commentUpdatedCredentials.getText());
//                return persistComment(commentEntityModel);
//          }).orElseThrow(() -> new ResourceNotFoundException(String.format("Comment not found with id %d, " +
//                  "could not update Comment under this post.", commentId)));

        }
    }

    @Transactional
    public ResponseEntity<?> deleteComment(Long postId, Long commentId) {
        /* First way to do it */
        return commentRepository.findById(commentId).map(commentToDelete -> {
            commentRepository.delete(commentToDelete);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(String.format("Comment not found with id %d, " +
                "could not update Comment under this post.", commentId)));
        /* Alternative way */
//        return commentRepository.findByIdAndPostId(commentId, postId).map(commentToDelete -> {
//            commentRepository.delete(commentToDelete);
//            return ResponseEntity.ok().build();
//        }).orElseThrow(() -> new ResourceNotFoundException(String.format("Comment not found with id %d, " +
//                "could not update Comment under this post.", commentId)));
    }

    @Transactional
    private CommentEntityModel persistComment(CommentEntityModel comment) {
        return commentRepository.save(comment);
    }
}
