package streamy;

import org.junit.jupiter.api.Test;

import static streamy.assertj.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class GeneratedAssertThatTest {

  @Test
  void pojoAsserts() {
    assertThat(Winner.winner2007)
      .hasYear(2006)
      .hasAveSpeedCloseTo(30, 10)
      .hasName("Alberto Contador")
      .hasLengthKm(2000)
    ;
  }

  @Test
  void collectionAsserts() {
    assertThat(Winner.tdfWinners)
      .anySatisfy(w -> assertThat(w)
        .hasName("Alberto Contador")
      );
  }

}
