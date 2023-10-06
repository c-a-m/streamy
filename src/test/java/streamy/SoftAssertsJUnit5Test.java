package streamy;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SoftAssertionsExtension.class)
class SoftAssertsJUnit5Test {
  @InjectSoftAssertions
  SoftAssertions softly;
  @Test
  void test() {
    Winner w2006 = Winner.winner2006;
    softly.assertThat(w2006.year()).isEqualTo(2005);
    softly.assertThat(w2006.name()).contains("Lance");
    softly.assertThat(w2006.nationality()).contains("United");
    softly.assertThat(w2006.lengthKm()).isGreaterThan(4000);
    softly.assertThat(w2006.getAveSpeed()).isLessThan(25);
  }
}
