package streamy;

import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class SoftAssertsTest {
  @Test
  void softInstance() {
    SoftAssertions softly = new SoftAssertions();
    Winner w2006 = Winner.winner2006;
    softly.assertThat(w2006.year()).isEqualTo(2005);
    softly.assertThat(w2006.name()).contains("Lance");
    softly.assertThat(w2006.nationality()).contains("United");
    softly.assertThat(w2006.lengthKm()).isGreaterThan(4000);
    softly.assertThat(w2006.getAveSpeed()).isLessThan(40);
//    softly.assertAll();
  }

  @Test
  void softLambda() {
    Winner w2006 = Winner.winner2006;
    SoftAssertions.assertSoftly(
      softly -> {
        softly.assertThat(w2006.year()).isEqualTo(2005);
        softly.assertThat(w2006.name()).contains("Lance");
        softly.assertThat(w2006.nationality()).contains("United");
        softly.assertThat(w2006.lengthKm()).isGreaterThan(4000);
        softly.assertThat(w2006.getAveSpeed()).isLessThan(40);
      });
  }

  @Test
  void softClosable() {
    Winner w2006 = Winner.winner2006;
    try(AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
      softly.assertThat(w2006.year()).isEqualTo(2005);
      softly.assertThat(w2006.name()).contains("Lance");
      softly.assertThat(w2006.nationality()).contains("United");
      softly.assertThat(w2006.lengthKm()).isGreaterThan(4000);
      softly.assertThat(w2006.getAveSpeed()).isLessThan(40);
    }
  }
}
