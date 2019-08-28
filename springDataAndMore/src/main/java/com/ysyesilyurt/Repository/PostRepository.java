package com.ysyesilyurt.Repository;

import com.ysyesilyurt.EntityModel.CommentEntityModel;
import com.ysyesilyurt.EntityModel.PostEntityModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<PostEntityModel, Long> {

    Optional<PostEntityModel> findById(Long id);

    Optional<PostEntityModel> findByTitle(String title);

    /* Methods that return Page needs a Pageable as argument !! */
    Page<PostEntityModel> findByCategory(String category, Pageable pageable);
           // TODO: convert Page to Collection or sth later on.
}
