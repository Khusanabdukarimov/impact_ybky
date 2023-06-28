package org.ykby.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.ykby.dto.Book;

import org.ykby.service.RoomDateService;
import org.ykby.service.RoomService;


@RestController
@RequestMapping("/api/rooms")
@AllArgsConstructor
public class RoomController {
  private final RoomService service;
  private final RoomDateService dateService;
  @GetMapping("/{id}")
  public ResponseEntity<?> getRoom(@PathVariable("id") Integer id){
    return ResponseEntity.ok(service.retrieveRoom(id));
  }

  @GetMapping("{num}/availability")
  public ResponseEntity<?> getAvailability(@PathVariable("num") Integer id,
                                           @RequestParam(value = "date", required = false)
                                           @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
    return ResponseEntity.ok(service.getAvailability(id, date));
  }

  @PostMapping("/{room_id}/book/")
  public ResponseEntity<?> bookRoom(@PathVariable("room_id") Integer room_id, @RequestBody Book booking) {
    dateService.bookRoom( booking, room_id);
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(Map.of("message", "xona muvaffaqiyatli band qilindi"));
  }

  @GetMapping
  public ResponseEntity<?> getRoomsThroughPage(@RequestParam(value = "page_size", required = false) Integer page_size,
                                               @RequestParam(value = "page", required = false) Integer page,
                                               @RequestParam(value = "type", required = false) String type,
                                               @RequestParam(value = "search", required = false) String search) {
    if(Objects.isNull(page) || Objects.isNull(page_size)) {
      page = 0;
      page_size = 9;
    }
    return ResponseEntity
        .ok(service.getRooms(page, page_size, type, search));
  }
}
