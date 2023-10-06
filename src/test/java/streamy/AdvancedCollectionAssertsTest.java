package streamy;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static streamy.CollectionAssertsTest.CookingInstruction.*;

class AdvancedCollectionAssertsTest {
  @Test
  void extracting() {
    assertThat(Winner.tdfWinners)
      .extracting("Year") //how to get this type correct?
//      .extracting(Winner::year)
//      .allMatch(year -> year < 2000 && year > 2021)
//      .allSatisfy(year -> assertThat(year)
//        .isBetween(2000, 2014))
    ;
  }

  @Test
  void notExtracting() {
    assertThat(Winner.tdfWinners)
      .allSatisfy(winner -> assertThat(winner.year())
        .isBetween(2000, 2014))
      .noneSatisfy(winner -> assertThat(winner.lengthKm())
        .isLessThan(3000));
  }
}
