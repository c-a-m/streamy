package streamy;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static streamy.assertj.custom.WinnerAssert.*;

class CustomAssertThatTest {

  @Test
  void isFaster() {
    assertThat(Winner.winner2007)
      .isFasterThan(Winner.winner2008);
  }
}
