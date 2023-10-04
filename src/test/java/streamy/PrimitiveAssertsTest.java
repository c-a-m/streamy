package streamy;

import java.nio.file.FileSystems;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class PrimitiveAssertsTest {

  @Test
  void bool() {
    assertTrue(System.err.checkError());
  }

  @Test
  void boolAJ() {
    assertThat(System.err.checkError())
      .isTrue()
      .isEqualTo(Boolean.TRUE);
  }

  @Test
  void longy() {
    assertEquals(500000000 , Runtime.getRuntime().totalMemory());
  }

  @Test
  void longAJ() {
    assertThat(Runtime.getRuntime().totalMemory())
      .isEven()
      .isEqualTo(500000000);
  }

  @Test
  void floaty() {
    assertEquals(3.14, Math.PI);
  }

  @Test
  void floatyAJ() {
    assertThat(Math.PI)
      .isPositive()
      .isEqualTo(3.14);
  }

  @Test
  void character() {
    assertEquals('\\', FileSystems.getDefault().getSeparator().charAt(0));
  }

  @Test
  void characterAJ() {
      assertThat(FileSystems.getDefault().getSeparator().charAt(0))
        .inUnicode()
        .isEqualTo('\\');
  }
}
