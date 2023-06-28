package org.ykby.service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.ykby.dto.DateDto;
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
  private final RoomDateService service;
  private final LocalDate TODAY = LocalDate.now();




  public Room retrieveRoom(Integer id) {
    Optional<Room> room = repository.findById(id);

    if(room.isEmpty()){
      throw new NotFound("topilmadi");
    }

    return room.get();
  }


  public List<DateDto> getAvailability(Integer room_id, LocalDate date) {
    LocalDate dateTime = TODAY;

    if(!Objects.isNull(date)) {

      dateTime = date;
    }

    LocalDate finalDateTime = dateTime;
    List<RoomDate> list =  dateRepository
        .findAll()
        .stream()
        .filter(e -> e.getRoom().getId().equals(room_id))
        .filter(x -> x.getStart().getDayOfYear() == finalDateTime.getDayOfYear()
            && x.getEnd().getDayOfYear() == finalDateTime.getDayOfYear())
        .toList();
    if(list.size() == 0) {
      service.setUpDateFixed(TODAY, room_id);
    }
    List<RoomDate> dates = dateRepository
        .findAll()
        .stream()
        .filter(e -> e.getRoom().getId().equals(room_id))
        .filter(x -> x.getStart().getDayOfYear() == finalDateTime.getDayOfYear()
            && x.getEnd().getDayOfYear() == finalDateTime.getDayOfYear())

        .toList();

    return updateAvailability(dates,dateTime);
  }
  //todo search: Xona nomi orqali qidirish
  //todo type: xona turi bo'yicha saralash (focus, team, conference)
  public PageDto getRooms(int page, int offset, String type, String search) {
    List<Room> list = repository.findByName(search);
    if(!Objects.isNull(type)) {
      Pageable pageable = PageRequest.of(page, offset);
      list = repository.findAll(pageable).stream().filter(e -> e.getType().equals(type)).toList();

    }

    if(list.size() == 0)
      list.add(retrieveRoom(1));

    return new PageDto(page, list.size(), offset, list);
  }


  private List<DateDto> updateAvailability(List<RoomDate> list, LocalDate dateTime) {

    List<DateDto> res = new LinkedList<>();
      for(RoomDate date : list) {
        res.add(new DateDto(date.getStart(), date.getEnd()));
      }
      return res;
  }

}
