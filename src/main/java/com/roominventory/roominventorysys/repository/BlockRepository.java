package com.roominventory.roominventorysys.repository;

import com.roominventory.roominventorysys.model.Block;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockRepository extends CrudRepository<Block, Integer> {
}
