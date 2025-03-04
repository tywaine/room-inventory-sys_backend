package com.roominventory.roominventorysys.repository;

import com.roominventory.roominventorysys.model.Furniture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FurnitureRepository extends CrudRepository<Furniture, Integer> {
}
