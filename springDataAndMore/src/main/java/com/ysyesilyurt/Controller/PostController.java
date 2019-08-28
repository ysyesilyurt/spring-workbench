package com.ysyesilyurt.Controller;

import com.ysyesilyurt.EntityModel.PostEntityModel;
import com.ysyesilyurt.Service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

    /*
     *   Injection through Constructor
     * */
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public Page<PostEntityModel> getAllPosts(Pageable pageable) {
        return postService.getAllPosts(pageable);
    }

    /*

     */
}
