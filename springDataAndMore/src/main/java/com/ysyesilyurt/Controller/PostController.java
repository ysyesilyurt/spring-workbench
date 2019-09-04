package com.ysyesilyurt.Controller;

import com.ysyesilyurt.EntityModel.PostEntityModel;
import com.ysyesilyurt.Enum.Category;
import com.ysyesilyurt.Service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Page<PostEntityModel> getAllPosts(Pageable pageable,
                                             @RequestParam(value = "title", required = false) String title,
                                             @RequestParam(value = "category", required = false) Category category) {
        return postService.getAllPosts(title, category, pageable);
    }

    @GetMapping("/{id}")
    public PostEntityModel getPostById(@PathVariable("id") Long id) {
        return postService.getPostById(id);
    }

    @PostMapping
    public PostEntityModel createPost(@RequestBody PostEntityModel post) {
        return postService.createPost(post);
    }

    @PutMapping("/{id}")
    public PostEntityModel updatePost(@PathVariable("id") Long id, @RequestBody PostEntityModel postUpdatedCredentials) {
        return postService.updatePost(id, postUpdatedCredentials);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id) {
        return postService.deletePost(id);
    }
}
