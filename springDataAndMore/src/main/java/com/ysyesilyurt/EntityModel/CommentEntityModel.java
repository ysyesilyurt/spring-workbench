package com.ysyesilyurt.EntityModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "comment")
public class CommentEntityModel extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid")
    private Long id;

    @NotNull
    @Lob
    @Column(name = "text")
    private String text;

    @JsonIgnore // to ignore the post object as a field of comment object in JSON
    /*
        If we do not put such an annotation we would bump into an infinite loop in the representation of Comment data
        since Post and Comment point to each other -- Comment data would show its Post and Post will show again its comments

        So we ignore the data in comment model to show them on post model.
        To overcome this problem we could have used a DTO or just a different model (such as Comment) that represents
        this model but apart from its Entity model.

        In this way we would ended up with a customized data, I will practice it in the next workbench (spring-workbench-3)
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false) // to declare that it has a many-to-one relationship
    @JoinColumn(name = "post_id", nullable = false) // name of the primary key of Post table in Comment table (btw name of the column does not imply any tiny relation on the relationship between entities)
    @OnDelete(action = OnDeleteAction.CASCADE) /* cascade on delete -- delete comments of a post when post gets deleted. */
    private PostEntityModel post; /* With providing the entity model's type here, Hibernate resolves that this
                                     relationship is between host model (comment) and this variable's model (post) */
}
