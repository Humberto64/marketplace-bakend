package com.troyecto.marketplace.dtos;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;
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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long productId;
    private String productName;
    private Long userId;
    private String userName;

    // Comentarios:
    // - Este DTO expone sólo los ids y nombres necesarios para la API, no las entidades JPA completas.
    // - createdAt/updatedAt: se muestran para que el cliente vea auditoría; su valor se debe establecer en el servicio.
    // - Nota: hay anotaciones Lombok redundantes (@Data ya incluye getters/setters). No es crítico, pero puede limpiarse.
}
