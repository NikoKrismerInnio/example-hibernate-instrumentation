package io.myplant.example.hibernate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity(name = "asset_detail")
@NoArgsConstructor
@AllArgsConstructor
public class AssetDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Exclude
    private int id;

    @Column
    private String customer;

    @Column
    private String location;

    @Column
    private String region;
}