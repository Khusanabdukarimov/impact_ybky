package org.ykby.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@Entity(name = "room_dates")
public class RoomDate {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer book_id;


  @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
  private LocalDateTime start;

  @Column(name = "end_")

  @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
  private LocalDateTime end;
  @ManyToOne
  @JoinColumn(name = "room_id")
  private Room room;

  @Override
  public String toString() {
    return "RoomDate{" +
        "book_id=" + book_id +
        ", start=" + start +
        ", end=" + end +
        ", room=" + room +
        '}';
  }
}
