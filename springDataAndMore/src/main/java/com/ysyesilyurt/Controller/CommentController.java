package com.ysyesilyurt.Controller;

import com.ysyesilyurt.EntityModel.CommentEntityModel;
import com.ysyesilyurt.Service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping // TODO: Play with pageable -- search it in RequestParam
    public Page<CommentEntityModel> getAllComments(@PathVariable(value = "postId") Long postId, Pageable pageable) {
        return commentService.getAllCommentsByPostId(postId, pageable);
    }


    /*

     */
}