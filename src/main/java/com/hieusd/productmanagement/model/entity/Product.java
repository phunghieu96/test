package com.hieusd.productmanagement.model.entity;

import com.hieusd.productmanagement.constants.ProductStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "sku")
    private String sku;

    @Column(name = "status")
    private ProductStatus status;

    @Column(name = "code", updatable = false, unique = true)
    private String code;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @PrePersist
     public void prePersist(){
        this.code = category.getCode() + this.sku + LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
        this.status = ProductStatus.valueOf("ACTIVE");
    }
}
