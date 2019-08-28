package com.ysyesilyurt.Service;

import com.ysyesilyurt.EntityModel.CommentEntityModel;
import com.ysyesilyurt.Exception.ResourceNotFoundException;
import com.ysyesilyurt.Repository.CommentRepository;
import com.ysyesilyurt.Repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
         return commentRepository.findByPostId(postId, pageable);
    }

    public CommentEntityModel createComment(Long postId, CommentEntityModel commentEntityModel) {
        return postRepository.findById(postId).map(postEntityModel -> {
            commentEntityModel.setPost(postEntityModel);
            return commentRepository.save(commentEntityModel);
        }).orElseThrow(() -> new ResourceNotFoundException(String.format("PostId %ld not found, " +
                "could not create Comment under this post.", postId)));
    }

    public CommentEntityModel updateComment(Long postId, Long commentId, CommentEntityModel commentUpdatedCredentials) {
        /* User can change everything in comment except its Post */

        if (!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException(String.format("PostId %ld not found, " +
                    "could not update Comment under this post.", postId));
        }
        else {
//          return commentRepository.findByIdAndPostId(commentId, postId).map(commentEntityModel -> {
//                commentEntityModel.setText(commentUpdatedCredentials.getText());
//                return commentRepository.save(commentEntityModel);
//          }).orElseThrow(() -> new ResourceNotFoundException(String.format("Comment not found with id %ld, " +
//                  "could not update Comment under this post.", commentId)));
            return commentRepository.findById(commentId).map(commentEntityModel -> {
                // User can not update comment's id
                commentEntityModel.setText(commentUpdatedCredentials.getText());
                return commentRepository.save(commentEntityModel);
            }).orElseThrow(() -> new ResourceNotFoundException(String.format("Comment not found with id %ld, " +
                    "could not update Comment under this post.", commentId)));
        }
    }

    public ResponseEntity<?> deleteComment(Long postId, Long commentId) {
//        return commentRepository.findByIdAndPostId(commentId, postId).map(commentEntityModel -> {
//            commentRepository.delete(commentEntityModel);
//            return ResponseEntity.ok().build();
//        }).orElseThrow(() -> new ResourceNotFoundException(String.format("Comment not found with id %ld, " +
//                "could not update Comment under this post.", commentId)));
        return commentRepository.findById(commentId).map(commentEntityModel -> {
            commentRepository.delete(commentEntityModel);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(String.format("Comment not found with id %ld, " +
                "could not update Comment under this post.", commentId)));
    }

}
