package com.hieusd.productmanagement.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "warehouse_product")
public class WareHouseProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", nullable = false)
    private WareHouse wareHouse;

    @Column(name = "inventory")
    private Long inventory;

    @Column(name = "total_import")
    private Long totalImport;

    @Column(name = "total_export")
    private Long totalExport;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "expired_date")
    private LocalDateTime expiredDate;
}
