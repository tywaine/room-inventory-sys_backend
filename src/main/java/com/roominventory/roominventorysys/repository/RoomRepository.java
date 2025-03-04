package com.roominventory.roominventorysys.repository;

import com.roominventory.roominventorysys.model.Room;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room, Integer> {

    @Query("SELECT COUNT(*) > 0 FROM Rooms r WHERE r.room_number = :roomNumber")
    boolean existsByRoomNumber(@Param("roomNumber") String roomNumber);
}
