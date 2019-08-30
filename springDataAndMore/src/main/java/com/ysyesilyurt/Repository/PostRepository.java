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

    /*
       We are returning Pages of our model to the client with the help of Spring Data's PagingRepo
       because of its handiness for retrieving data sorted and filtered according to its meta.

       We could have also directly used List<PostEntityModel> or Collection etc.
     */

    Page<PostEntityModel> findByTitleAndCategory(String title, String category, Pageable pageable);

    Page<PostEntityModel> findByTitle(String category, Pageable pageable);

    /* Methods that return Page needs a Pageable as argument !! */
    Page<PostEntityModel> findByCategory(String category, Pageable pageable);
}
