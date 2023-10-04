package streamy;

import java.time.Duration;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class AdvancedPojosTest {

  @Test
  void hasNoNullProperties() {
    assertThat(Winner.winner2007)
      .hasNoNullFieldsOrProperties()
      .hasFieldOrPropertyWithValue("year", 2006);
  }

  @Test
  void isEqualTo() {
    assertThat(Winner.winner2007)
//          .usingRecursiveComparison()
//          .ignoringFieldsOfTypes(Duration.class)
//          .ignoringFields("lengthKm")
          .isEqualTo(new Winner(2007, "spain", "Alberto Contador", "Discovery Channel", 1000, Duration.ZERO));

    //pojo.isEqualTo
    //
  }

  @Test
  void extracting() {
    assertThat(Winner.winner2007)
      .extracting("length", INTEGER)
      .isBetween(3800, 4000);

    //lambda is better: mistype, or refactor
    //Types
    //no going back
  }

  @Test
  void matchers() {
    assertThat(Winner.winner2007)
      .matches(winner -> winner.name().contains("alberto") && winner.getAveSpeed() > 40 && winner.year() == 2006);
  }

  @Test
  void satisfiesCondition() {
    assertThat(Winner.winner2007)
      .satisfies(new Condition<>(winner -> winner.name().contains("alberto"), "has name"));

    //how is this any better than assertTrue(hasCondition())???
  }

  @Test
  void satisfiesNestedAssertThat() {
    assertThat(Winner.winner2007)
      .satisfies(winner -> assertThat(winner.name()).contains("alberto"))
      .satisfies(winner -> assertThat(winner.lengthKm()).isGreaterThan(200));

    //OK on pojos, But try it on collections
  }
}
