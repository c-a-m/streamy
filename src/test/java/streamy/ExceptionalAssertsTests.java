package streamy;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ExceptionalAssertsTests {

  @Test
  void throwsException() {
    Throwable exception = assertThrows(RuntimeException.class, () ->
      "thing".getBytes("UTF42")
      );
  }

  @Test
  void throwsExceptionAJ() {
    assertThatThrownBy(() -> "thing".getBytes("UTF42"))
      .isInstanceOf(IOException.class)
      .isExactlyInstanceOf(UnsupportedEncodingException.class)
    ;
  }

  @Test
  void throwsNestedException() {
    RuntimeException myException = assertThrows(
      RuntimeException.class, () -> getBytes("thing", "UTF42"));

    assertEquals(RuntimeException.class, myException.getCause().getClass());
    assertEquals("Unable to encode with this format: UTF42", myException.getMessage());
  }

  @Test
  void throwsNestedExceptionAJ() {
    assertThatThrownBy(() -> getBytes("thing", "UTF42"))
      .isInstanceOf(Runtime.class)
      .hasMessageContaining("utf42")
      .hasRootCauseInstanceOf(AssertionError.class)
      .hasRootCauseExactlyInstanceOf(IOException.class)
    ;
  }

  private byte[] getBytes(String toEncode, String encoding) throws MyException {
    try {
      return toEncode.getBytes(encoding);
    }
    catch (UnsupportedEncodingException e) {
      throw new MyException("Can't encode with this format: " + encoding,e);
    }
  }

  private class MyException extends Exception {
    MyException(String message, Throwable cause) {
      super(message, cause);
    }
  }
}
