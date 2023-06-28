package org.ykby.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record Book(Resident resident, @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime start, @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime end) {
}
