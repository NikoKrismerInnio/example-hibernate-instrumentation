package io.myplant.example.hibernate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name = "property")
@NoArgsConstructor
@AllArgsConstructor
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "asset_id")
    public int assetId;

    @Column
    public String name;

    @Column
    public String value;

    public Property(final String name, final String value) {
        this.name = name;
        this.value = value;
    }
}
