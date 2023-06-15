package org.ykby.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ykby.entity.Room;


public interface RoomRepository extends JpaRepository<Room, Integer> {
}
