package io.myplant.example.hibernate.repository;

import io.myplant.example.hibernate.model.AssetDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetDetailRepository extends JpaRepository<AssetDetail, Integer> {

}
