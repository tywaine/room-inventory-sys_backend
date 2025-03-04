package com.roominventory.roominventorysys.repository;

import com.roominventory.roominventorysys.model.Occupant;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OccupantRepository extends CrudRepository<Occupant, Integer> {

    @Query("SELECT * FROM Occupants WHERE id_number = :idNumber")
    Optional<Occupant> findByIdNumber(@Param("idNumber") String idNumber);

    @Query("SELECT * FROM Occupants WHERE email = :email")
    Optional<Occupant> findByEmail(@Param("email") String email);

    @Query("SELECT * FROM Occupants WHERE phone_number = :phoneNumber")
    Optional<Occupant> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
