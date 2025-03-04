package com.roominventory.roominventorysys.repository;

import com.roominventory.roominventorysys.model.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT * FROM Users WHERE username = :username")
    Optional<User> findByUsername(@Param("username") String username);

    @Query("SELECT COUNT(*) FROM Users WHERE role = 'ADMIN'")
    Integer countByAdmin();
}
