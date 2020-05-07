package io.myplant.example.hibernate.repository;

import io.myplant.example.hibernate.model.DataItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataItemRepository extends JpaRepository<DataItem, Integer> {
}
