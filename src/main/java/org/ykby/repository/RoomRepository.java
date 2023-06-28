package org.ykby.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.ykby.entity.Room;


public interface RoomRepository extends JpaRepository<Room, Integer> {
  List<Room> findByName(String name);
}
