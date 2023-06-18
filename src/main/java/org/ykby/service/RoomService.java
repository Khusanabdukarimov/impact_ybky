package org.ykby.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.ykby.dto.PageDto;
import org.ykby.entity.Room;
import org.ykby.entity.RoomDate;
import org.ykby.exception.NotFound;
import org.ykby.repository.DateRepository;
import org.ykby.repository.RoomRepository;

@Service
@AllArgsConstructor
public class RoomService {


  private final RoomRepository repository;

  private final DateRepository dateRepository;
  private final LocalDate TODAY = LocalDate.now();




  public Room retrieveRoom(Integer id) {
    Optional<Room> room = repository.findById(id);

    if(room.isEmpty()){
      throw new NotFound("topilmadi");
    }

    return room.get();
  }


  public List<RoomDate> getAvailability(Integer room_id, LocalDate dateTime) {

    if(Objects.isNull(dateTime))
      dateTime = TODAY;

    LocalDate finalDateTime = dateTime;
    return dateRepository
        .findAll()
        .stream()
        .filter(e -> e.getRoom().getRoomId().equals(room_id))
        .filter(x -> x.getStart().getDayOfYear() == finalDateTime.getDayOfYear()
            && x.getEnd().getDayOfYear() == finalDateTime.getDayOfYear())
        .toList();
  }

  public PageDto getRooms(int page, int offset, String sort) {
    Pageable pageable = PageRequest.of(page, offset, Sort.by(sort));
    Page<Room> pages = repository.findAll(pageable);

    return new PageDto(page, pages.toList().size(), offset, pages.toList());
  }

}
