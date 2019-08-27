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
public class Comment extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid")
    private Long id;

    @NotNull
    @Lob
    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY, optional = false) // to declare that it has a many-to-one relationship with the Post entity
    @JoinColumn(name = "post_id", nullable = false) // to declare the foreign key column and name it on comment table
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore // to ignore the post on serialization and deserialization
    private Post post;
}
