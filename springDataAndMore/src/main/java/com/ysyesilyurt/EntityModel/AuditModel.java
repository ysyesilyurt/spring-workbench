package com.ysyesilyurt.EntityModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
    Since both Post and Comment entities contain some common auditing related fields like created_at and updated_at,
    I am going to abstract out these common fields in a separate class called AuditModel
    and extend this class in the Post and Comment entities.

    For these common fields actually we are gonna use Spring Boot’s JPA Auditing feature to automatically populate
    the created_at and updated_at fields while persisting the entities.
 */

@MappedSuperclass // Designates a class whose mapping information is applied to the entities that inherit from it.
                  // A mapped superclass has no separate table defined for it.
@EntityListeners(AuditingEntityListener.class) // We are going to use Spring Boot’s AuditingEntityListener class and it
                                               // it will automatically
@JsonIgnoreProperties( // ignores the specified logical properties in JSON serialization and deserialization.
        value = {"createdAt", "updatedAt"},
        allowGetters = true
)
@Setter
@Getter
public abstract class AuditModel implements Serializable {

    @Temporal(TemporalType.TIMESTAMP) // @Temporal is a JPA annotation which can be used to store in the
                                      // database table on of date, time and timestamp items
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private Date updatedAt;

}