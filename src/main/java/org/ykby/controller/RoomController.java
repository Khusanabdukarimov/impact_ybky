package org.ykby.controller;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
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

  @GetMapping("/{room}/available")
  public ResponseEntity<?> getAvailability(@PathVariable("room") Integer id,
                                           @RequestParam(value = "date", required = false) LocalDate date) {
    return ResponseEntity.ok(service.getAvailability(id, date));
  }

  @PostMapping("/{room_id}/book")
  public ResponseEntity<?> bookRoom(@PathVariable("room_id") Integer room_id, @RequestBody Book booking) {
    return ResponseEntity.status(HttpStatus.CREATED).body(dateService.bookRoom( booking, room_id));
  }

  @GetMapping
  public ResponseEntity<?> getRoomsThroughPage(@RequestParam String type,
                                               @RequestParam Integer page_size,
                                               @RequestParam Integer page) {
    return ResponseEntity.ok(service.getRooms(page, page_size, type));
  }

}
