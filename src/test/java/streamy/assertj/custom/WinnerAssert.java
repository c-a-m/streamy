package streamy.assertj.custom;

import org.assertj.core.api.AbstractAssert;
import streamy.Winner;

public class WinnerAssert extends AbstractAssert<WinnerAssert, Winner> {
  public WinnerAssert(Winner winner) {
    super(winner, WinnerAssert.class);
  }

  public static WinnerAssert assertThat(Winner winner) {
    return new WinnerAssert(winner);
  }

  public WinnerAssert isFasterThan(Winner winner) {
    isNotNull(); //check that actual is not null
    if (actual.getAveSpeed() <= winner.getAveSpeed()) {
      failWithMessage("Expected winner's average speed to be greater than <%s>" +
                        " but was <%s>", winner.getAveSpeed(), actual.getAveSpeed());
    }
    return this;
  }
}
