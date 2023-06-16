package org.ykby.dto;

import java.util.List;
import org.ykby.entity.Room;

public record PageDto(
    Integer page,
    Integer count,
    Integer page_size,
    List<Room> results
) {
}
