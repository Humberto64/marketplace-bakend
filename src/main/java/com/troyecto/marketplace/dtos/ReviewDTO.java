package com.troyecto.marketplace.dtos;

import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewDTO {
    private Long id;
    private int rating;
    private String comment;
    private Date createdAt;
    private Date updatedAt;
}
