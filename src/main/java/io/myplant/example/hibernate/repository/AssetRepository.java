package io.myplant.example.hibernate.repository;

import io.myplant.example.hibernate.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Integer> {
    Optional<Asset> findByName(final String name);
}
