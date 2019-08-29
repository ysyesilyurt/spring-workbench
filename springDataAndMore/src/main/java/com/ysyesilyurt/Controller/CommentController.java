package com.ysyesilyurt.Controller;

import com.ysyesilyurt.EntityModel.CommentEntityModel;
import com.ysyesilyurt.Service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/posts/{postId}/comments")
public class CommentController {

    private CommentService commentService;

    /*
     *   Injection through Constructor
     * */
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping // for @PathVariable you dont need to explicitly specify name of the variable as the value
    public Page<CommentEntityModel> getAllComments(@PathVariable Long postId, Pageable pageable) {
        return commentService.getAllCommentsByPostId(postId, pageable);
    }

    @GetMapping("/{commentId}")
    public Optional<CommentEntityModel> getCommentById(@PathVariable Long commentId) {
        return commentService.getCommentById(commentId);
    }

    @PostMapping
    public CommentEntityModel createComment(@PathVariable Long postId,
                                            @RequestBody CommentEntityModel commentEntityModel) {
        return commentService.createComment(postId, commentEntityModel);
    }

    @PutMapping("/{commentId}")
    public CommentEntityModel updateComment(@PathVariable Long postId, @PathVariable Long commentId,
                                            @RequestBody CommentEntityModel commentUpdatedCredentials) {
        return commentService.updateComment(postId, commentId, commentUpdatedCredentials);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
        return commentService.deleteComment(postId, commentId);
    }
}