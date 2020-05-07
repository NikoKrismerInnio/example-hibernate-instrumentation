package io.myplant.example.hibernate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name = "data_item")
@NoArgsConstructor
@AllArgsConstructor
public class DataItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "asset_id")
    public int assetId;

    @Column
    public String name;

    @Column
    public String value;

    public DataItem(final String name, final String value) {
        this.name = name;
        this.value = value;
    }
}
