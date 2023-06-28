package org.ykby.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "rooms")
public class Room {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "room_id")
  private Integer id;
  private String name;
  private Integer capacity;
  private String type;

  @Override
  public String toString() {
    return "Room{" +
        "roomId=" + id +
        ", name='" + name + '\'' +
        ", capacity=" + capacity +
        ", type=" + type +
        '}';
  }
}
