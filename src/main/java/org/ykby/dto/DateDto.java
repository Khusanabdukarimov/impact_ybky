package org.ykby.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

public record DateDto(@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime start, @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime end) {
}
