package com.troyecto.marketplace.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.cdi.Eager;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int rating;
    @Column(nullable = false)
    private String comment;
    //@Column(nullable = false)
    private Date createdAt;
    //@Column(nullable = false)
    private Date updatedAt;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",
        nullable = false,
            foreignKey =@ForeignKey(name ="fk_review_user"))
    @JsonBackReference
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id",
            nullable = false,
            foreignKey =@ForeignKey(name ="fk_review_product"))
    @JsonBackReference
    private Product product;
}
