package org.ykby.dto;

import java.time.LocalDateTime;

public record Book(Resident resident, LocalDateTime start, LocalDateTime end) {
}
