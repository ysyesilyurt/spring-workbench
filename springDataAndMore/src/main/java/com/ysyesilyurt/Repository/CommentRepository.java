package com.ysyesilyurt.Repository;

import com.ysyesilyurt.EntityModel.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
