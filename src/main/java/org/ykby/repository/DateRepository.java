package org.ykby.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.ykby.entity.RoomDate;


public interface DateRepository extends JpaRepository<RoomDate, Integer> {

}
