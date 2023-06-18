package org.ykby.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import org.ykby.util.Type;

@Getter
@Setter
@Entity(name = "rooms")
public class Room {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer roomId;
  private String name;
  private Integer capacity;
  private Type type;

  @Override
  public String toString() {
    return "Room{" +
        "roomId=" + roomId +
        ", name='" + name + '\'' +
        ", capacity=" + capacity +
        ", type=" + type +
        '}';
  }
}
