package io.myplant.example.hibernate.model;

import lombok.*;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Blob;
import java.util.List;

@Data
@Entity(name = "asset")
@NoArgsConstructor
@AllArgsConstructor
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "blob", columnDefinition = "BLOB")
    @Type(type = "org.hibernate.type.BinaryType")
    @ToString.Exclude
    @LazyGroup("lob")
    private byte[] blob;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_id", referencedColumnName = "id")
    @ToString.Exclude
    private List<DataItem> dataItems;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_id", referencedColumnName = "id")
    @ToString.Exclude
    private List<Property> properties;

    @JoinColumn(name = "id", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @ToString.Exclude
    private AssetDetail details;
}