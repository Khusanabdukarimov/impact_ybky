package org.ykby.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.ykby.dto.Book;
import org.ykby.entity.Room;
import org.ykby.entity.RoomDate;
import org.ykby.exception.GoneException;
import org.ykby.repository.DateRepository;
import org.ykby.repository.RoomRepository;

@Service
@AllArgsConstructor
public class RoomDateService {
  private final DateRepository repository;
  private final RoomRepository roomRepository;
  private final LocalDate TODAY = LocalDate.now();
  private final LocalTime START_FIXED = LocalTime.of(0, 0, 0);
  private final LocalTime END_FIXED = LocalTime.of(23, 59, 59);


  public boolean bookRoom(Book booking, Integer room_id) {
    List<RoomDate> availability = getDatesFromGivenDate(booking.start().toLocalDate(), room_id);

    if (!checkOfMiddleBooking(availability, booking.start(), booking.end())) {
      throw new GoneException("uzr, siz tanlagan vaqtda xona band");
    }

    if (availability.size() < 1) {
      setUpDateFixed(booking.start().toLocalDate(), room_id);
      availability = getDatesFromGivenDate(booking.start().toLocalDate(), room_id);
    }

    List<RoomDate> duration = availability.stream()
        .filter(x -> x.getStart().getHour() <= booking.start().getHour())
        .toList();

    if(duration.size()==0) throw new GoneException("bu xona band qilingan");

    RoomDate startBook = duration.get(duration.size() - 1);
    System.out.println(startBook.getStart() + " " + startBook.getEnd());
    repository.delete(startBook);

    RoomDate endBook = new RoomDate();
    LocalDateTime time = booking.end();
    endBook.setStart(time);
    endBook.setEnd(startBook.getEnd());
    endBook.setRoom(startBook.getRoom());
    startBook.setEnd(booking.start());


    if (!startBook.getStart().equals(startBook.getEnd())) {
      repository.save(startBook);
    }
    if(!endBook.getStart().equals(endBook.getEnd()))repository.save(endBook);

    return true;
  }

  public List<RoomDate> getDatesFromGivenDate(LocalDate date, Integer id) {

    Optional<List<RoomDate>> dates = Optional
        .of(repository
            .findAll().stream().filter(x -> x.getRoom() != null)
            .filter(x -> x.getRoom().getId().equals(id)).toList()
            .stream()
            .filter(e -> e.getStart()
                .toLocalDate().equals(date))
            .collect(Collectors.toList()));

    return dates.get();
  }

  public void setUpDateFixed(LocalDate date, Integer roomId) {
    RoomDate roomDate = new RoomDate();
    Room d = roomRepository.findById(roomId).get();
    roomDate.setStart(LocalDateTime.of(date, START_FIXED));
    roomDate.setEnd(LocalDateTime.of(date, END_FIXED));
    roomDate.setRoom(d);
    repository.saveAndFlush(roomDate);
  }

  private boolean checkOfMiddleBooking(List<RoomDate> list, LocalDateTime start,
                                       LocalDateTime end) {
    int count = 0;
    RoomDate last = null;
    for (int i = 0; i < list.size(); i++) {
      RoomDate date = list.get(i);

      if (date.getStart().getHour() > start.getHour() && date.getEnd().getHour() < end.getHour()) {
        return false;
      }else if(start.getHour() == date.getEnd().getHour()
          && i != list.size()-1
          && end.getHour() == list.get(i+1).getStart().getHour()) {
         if( (date.getStart().getMinute() < start.getMinute()
             || list.get(i+1).getStart().getMinute() < end.getMinute()) ) return false;
         else if(list.get(i+1).getStart().getHour()>start.getMinute() && date.getEnd().getMinute()>end.getMinute())
           return false;

         if(start.getMinute() == date.getEnd().getMinute()
             && list.get(i+1).getStart().getMinute()==end.getMinute())
             return false;
      }

      if(i==list.size()-1){
        last=date;
      }
    }
      if(!Objects.isNull(last)) {
        if(last.getStart().getHour() == start.getHour() && last.getEnd().getHour() <= end.getHour()) {

          if(last.getStart().getMinute() < start.getMinute() || last.getEnd().getMinute() < end.getMinute())return false;
        }
        return last.getStart().getHour() <= start.getHour() && last.getEnd().getHour() >= end.getHour();
      }
    return true;
  }
}
