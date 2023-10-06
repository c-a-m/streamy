package streamy;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static streamy.CollectionAssertsTest.CookingInstruction.*;

class CollectionAssertsTest {

  @Test
  void arrays() {
    assertTrue(Arrays.stream(getPaths())
                 .anyMatch(s -> s.contains("jdk")), "one of the paths contains java");
  }

  @Test
  void arraysAJ() {
    assertThat(getPaths())
      .anyMatch(s -> s.contains("jdk"));
    //      .anySatisfy(s -> assertThat(s).contains("jdk"));
  }

  @Test
  void set() {
    assertTrue(getFruit().contains("apple"));
    assertTrue(getFruit().contains("tomato"));
    assertFalse(getFruit().contains("potato"));
  }

  @Test
  void setAJ() {
    assertThat(getFruit())
      .contains("tomato", "apple")
      .doesNotContain("potato");
  }

  @Test
  void list() {
    List<CookingInstruction> expectedInOrder = List.of(preheat, mix, pour, bake, serve);
    for (int i = 0; i < expectedInOrder.size(); i++) {
      assertEquals(preheat, cookingInstructions().get(i), "expected cooking order in position " + i);
    }
  }

  @Test
  void listAJ() {
    assertThat(cookingInstructions())
      .contains(wash_hands, serve)
      .containsExactly(preheat, mix, pour, bake, serve);;
  }

  private List<CookingInstruction> cookingInstructions() {
    return List.of(
      preheat, mix, pour, wash_hands, wait, let_cool, bake, serve
    );
  }

  private Set<String> getFruit() {
    return Set.of("bannana", "strawberry", "apple", "peach", "mango");
  }

  private static String[] getPaths() {
    return System.getenv("PATH").split(":");
  }

  static enum CookingInstruction {
    wash_hands,
    preheat,
    mix,
    pour,
    bake,
    wait,
    let_cool,
    serve
  }
}
