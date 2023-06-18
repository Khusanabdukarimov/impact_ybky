package org.ykby.exception;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
@ControllerAdvice
public class BadException  {
  @ExceptionHandler
  public ResponseEntity<Map<String, String>> exception(GoneException ex) {
    return ResponseEntity.status(HttpStatus.GONE).body(Map.of("error", ex.getMessage()));
  }

  @ExceptionHandler
  public ResponseEntity<?> notFound(NotFound ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", ex.getMessage()));
  }


}
