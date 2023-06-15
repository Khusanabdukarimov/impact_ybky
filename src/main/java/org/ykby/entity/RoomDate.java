package org.ykby.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "room_dates")
public class RoomDate {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer book_id;

  private LocalDateTime start;
  @Column(name = "end_")
  private LocalDateTime end;
  private Boolean active;
  @ManyToOne
  @JoinColumn(name = "room_id")
  private Room room;

  @Override
  public String toString() {
    return "RoomDate{" +
        "book_id=" + book_id +
        ", start=" + start +
        ", end=" + end +
        ", active=" + active +
        ", room=" + room +
        '}';
  }
}
