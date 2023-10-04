package streamy;

import java.nio.file.FileSystems;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import static java.time.LocalTime.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class CommonObjectAssertsTest {

  public static final Instant TEN_MINUTES_AGO = Instant.now().minus(10, ChronoUnit.MINUTES);

  @Test
  void string() {
    assertEquals("/users/cammorris", System.getenv("HOME"));
    assertTrue(System.getenv("HOME").contains("/users"), "Home directory contains /users");
  }

  @Test
  void stringAJ() {
    assertThat(System.getenv("HOME"))
      .startsWith("/users")
      .contains("cammorris");
  }

  @Test
  void date() {
    assertEquals(LocalDate.of(1776, Month.JULY, 4), get4thOfJuly());
  }

  @Test
  void dateAJ() {
    assertThat(get4thOfJuly())
      .hasMonth(Month.JULY)
      .hasDayOfMonth(4)
      .hasYear(1976);
  }

  @Test
  void instant() {
    assertTrue(Instant.now().isBefore(TEN_MINUTES_AGO));
  }
  @Test
  void instantAJ() {
    assertThat(Instant.now())
      .isBefore(TEN_MINUTES_AGO);
  }

  @Test
  void optional() {
    assertTrue(getThing().isPresent());
    assertEquals("thing", getThing().get());
  }

  @Test
  void optionalAJ() {
    assertThat(getThing())
      .isPresent()
      .get()
      .isInstanceOf(String.class)
      .isEqualTo("thing");
  }

  private Optional<String> getThing() {
    return Optional.empty();
//    return Optional.of("");
//    return Optional.of("thing");
  }

  private LocalDate get4thOfJuly() {
    return LocalDate.of(2023,Month.JULY, 4);
  }
}
