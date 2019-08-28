package com.ysyesilyurt.Repository;

import com.ysyesilyurt.EntityModel.CommentEntityModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntityModel, Long> {

    Optional<CommentEntityModel> findById(Long id);

    /* This method is actually unnecessary for a real application
     * (Since Comment and Post has one-to-many relation between each other)
     * It is added for testing purposes
     */
    Optional<CommentEntityModel> findByIdAndPostId(Long id, Long postId);

    Page<CommentEntityModel> findByPostId(Long postId, Pageable pageable);
}
