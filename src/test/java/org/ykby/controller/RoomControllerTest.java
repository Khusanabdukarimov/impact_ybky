package org.ykby.controller;



import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.ykby.dto.Book;
import org.ykby.dto.Resident;
import org.ykby.entity.Room;
import org.ykby.entity.RoomDate;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class RoomControllerTest {


  @Autowired
  private MockMvc mockMvc;

  @Order(1)
  @Test
  public void testRoomById() throws Exception{
    ObjectMapper objectMapper = new ObjectMapper();
    RequestBuilder builder = MockMvcRequestBuilders
        .get("/api/rooms/{id}", 5);

    String response = getResponse(builder, 200);

    ObjectReader reader = objectMapper.reader(new TypeReference<Room>() {
    });

    Room room = reader.readValue(response);
    System.out.println(room);
    Assertions.assertNotNull(response);
    Assertions.assertNotNull(room);
    Assertions.assertEquals(room.getCapacity(), 12);
  }

  @Test
  @Order(2)
  public void availableTest() throws Exception{
     ObjectMapper mapper = new ObjectMapper();
     mapper.registerModule(new JavaTimeModule());
     RequestBuilder builder = MockMvcRequestBuilders
         .get("/api/rooms/{room}/available", 3)
         .param("date", "2023-06-04");

     String response = getResponse(builder, 200);
     ObjectReader reader = mapper.reader(new TypeReference<List<RoomDate>>() {
     });


     Assertions.assertNotNull(response);
  }


  @Test
  @Order(3)
  public void bookRoomTest() throws Exception {
      ObjectMapper mapper = new ObjectMapper();
      mapper.registerModule(new JavaTimeModule());
      Book book = new Book(new Resident("Kamoliddin"), LocalDateTime.of(LocalDate.of(2023,6,4),
          LocalTime.of(9, 0, 0)), LocalDateTime.of(LocalDate.of(2023,6,4),
          LocalTime.of(12, 0, 0)));
      String content = mapper.writeValueAsString(book);
      RequestBuilder builder = MockMvcRequestBuilders
          .post("/api/rooms/{room}/book", 6)
          .content(content)
          .contentType(MediaType.APPLICATION_JSON);

      String response = getResponse(builder, 201);
      Assertions.assertNotNull(response);
  }
  private String getResponse(RequestBuilder builder, int status) throws Exception{
    return mockMvc
        .perform(builder)
        .andExpect(status().is(status))
        .andDo(print())
        .andReturn()
        .getResponse()
        .getContentAsString();
  }
}
