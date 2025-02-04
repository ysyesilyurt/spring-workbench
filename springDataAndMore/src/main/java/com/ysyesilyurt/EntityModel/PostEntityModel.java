package com.ysyesilyurt.EntityModel;

import com.ysyesilyurt.Enum.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "post")
public class PostEntityModel extends AuditModel{

    @Id // Specifies the primary key of an entity.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specification of generation strategies for the values of primary keys.
                   // IDENTITY strategy means database can automatically assign a next value, column will be of type auto increment.
    @Column(name = "pid")
    private Long id;

    @NotNull
    @Size(max = 75)
    @Column(name = "title", unique = true, nullable = false)
    private String title;

    @NotNull
    @Column(name = "category", nullable = false)
    private Category category;


    @Size(max = 300)
    @Column(name = "description", nullable = true)
    private String description;

    @NotNull
    @Lob // Means currently annotated entity attribute represents a large object type.
    @Column(name = "content")
    private String content;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "post")
//  @OrderBy("created_at DESC")
    private Set<CommentEntityModel> comments;
//    private List<CommentEntityModel> comments;

}
