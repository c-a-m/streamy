package streamy;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import java.util.*;

import static java.lang.System.out;
import static java.util.stream.Collectors.*;
import org.hamcrest.MatcherAssert;
import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.*;
import static streamy.Winner.tdfWinners;

/**
 * This example code comes from https://dzone.com/articles/a-java-8-streams-cookbook
 */
class WinnerTest {

  @Test
  void winnersOfToursLessThan3500km() {
    // Filter and Map -
    List<String> winnersOfToursLessThan3500km = tdfWinners
        .stream()
        .filter(d -> d.getLengthKm() < 3500) // Separate out Tours less than 3500km
        .map(Winner::getName) // Get names of winners
        .collect(toList()); // Return a list

    // Winners of Tours Less than 3500km - [Alberto Contador, Cadel Evans, Bradley Wiggins, Chris Froome, Chris Froome]
    out.println("Winners of Tours Less than 3500km - " + winnersOfToursLessThan3500km);

    assertThat(winnersOfToursLessThan3500km).containsExactly(
        "Alberto Contador", "Cadel Evans", "Bradley Wiggins", "Chris Froome", "Chris Froome"
    );
  }

  @Test
  void winnersOfToursGreaterThan3500km() {
    List<String> winnersOfToursGreaterThan3500km = tdfWinners
        .stream()
        .filter(d -> d.getLengthKm() >= 3500)
        .map(Winner::getName)
        .collect(toList());
    // Winners of Tours Greater than 3500km - [Óscar Pereiro, Alberto Contador, Carlos Sastre, Andy Schleck, Vincenzo Nibali, Chris Froome]

    out.println("Winners of Tours Greater than 3500km - " + winnersOfToursGreaterThan3500km);

    MatcherAssert.assertThat(winnersOfToursGreaterThan3500km,
        contains("Óscar Pereiro", "Alberto Contador", "Carlos Sastre", "Andy Schleck", "Vincenzo Nibali", "Chris Froome"));

    assertThat(winnersOfToursGreaterThan3500km).containsExactly(
        "Óscar Pereiro", "Alberto Contador", "Carlos Sastre", "Andy Schleck", "Vincenzo Nibali", "Chris Froome"
    );
  }

  @Test
  void winnersOfToursGreaterThan3500kmLimit2_Objects() {
    // limit -
    List<Winner> winnerObjectsOfToursLessThan3500kmLimit2 = tdfWinners
        .stream()
        .filter(d -> d.getLengthKm() < 3500)
        .limit(2)
        .collect(toList());

    // winnerObjectsOfToursLessThan3500kmLimit2 [Alberto Contador, Cadel Evans]
    out.println("winnerObjectsOfToursLessThan3500kmLimit2 " + winnerObjectsOfToursLessThan3500kmLimit2);
    MatcherAssert.assertThat(winnerObjectsOfToursLessThan3500kmLimit2,
        contains(
            hasProperty("name", equalTo("Alberto Contador")),
            hasProperty("name", equalTo("Cadel Evans"))
        ));

    assertThat(winnerObjectsOfToursLessThan3500kmLimit2)
        .extracting(Winner::getName)
        .containsExactly("Alberto Contador", "Cadel Evans");
  }

  @Test
  void distinctWinnders() {
    // filter by distinct
    List<String> distinctTDFWinners = tdfWinners
        .stream()
        .map(Winner::getName)
        .distinct()
        .collect(toList());

    out.println("distinctTDFWinners - " + distinctTDFWinners);
    MatcherAssert.assertThat(distinctTDFWinners,
        hasSize(8));

    assertThat(distinctTDFWinners).hasSize(8);
  }

  @Test
  void distinctWinnersCount() {
    long numberOfDistinceWinners = tdfWinners
        .stream()
        .map(Winner::getName)
        .distinct()
        .count();

    // numberOfDistinceWinners - 8
    out.println("numberOfDistinceWinners - " + numberOfDistinceWinners);
    MatcherAssert.assertThat(numberOfDistinceWinners, equalTo(8L));

    assertThat(numberOfDistinceWinners).isEqualTo(8);
  }

  @Test
  void allButTheFirst2Winners() {
    // skip records
    List<Winner> skipFirst2Winners = tdfWinners
        .stream()
        .skip(2)
        .collect(toList());
    // skipEveryOtherTDFWinner - [Carlos Sastre, Alberto Contador, Andy Schleck, Cadel Evans, Bradley Wiggins, Chris Froome, Vincenzo Nibali, Chris Froome, Chris Froome]
    out.println("skipEveryOtherTDFWinner - " + skipFirst2Winners);
    MatcherAssert.assertThat(skipFirst2Winners,
        everyItem(not(in(Arrays.asList(tdfWinners.get(0), tdfWinners.get(1)))))
    );

    assertThat(skipFirst2Winners)
        .doesNotContain(tdfWinners.get(0), tdfWinners.get(1));
  }

  @Test
  void winnerYearAndName() {
    List<String> mapWinnerYearNamesToList = tdfWinners
        .stream()
        .map(w -> w.getYear() + " - " + w.getName())
        .collect(toList());

    // mapWinnerYearNamesToList [2006 - Óscar Pereiro, 2007 - Alberto Contador, 2008 - Carlos Sastre, 2009 - Alberto Contador, 2010 - Andy Schleck, 2011 - Cadel Evans, 2012 - Bradley Wiggins, 2013 - Chris Froome, 2014 - Vincenzo Nibali, 2015 - Chris Froome, 2016 - Chris Froome]
    out.println("mapWinnerYearNamesToList " + mapWinnerYearNamesToList);
    MatcherAssert.assertThat(mapWinnerYearNamesToList,
        contains(
            "2006 - Óscar Pereiro", "2007 - Alberto Contador", "2008 - Carlos Sastre", "2009 - Alberto Contador", "2010 - Andy Schleck", "2011 - Cadel Evans", "2012 - Bradley Wiggins", "2013 - Chris Froome", "2014 - Vincenzo Nibali", "2015 - Chris Froome", "2016 - Chris Froome"
        ));

    assertThat(mapWinnerYearNamesToList).
        containsOnly(
            "2006 - Óscar Pereiro", "2007 - Alberto Contador", "2008 - Carlos Sastre", "2009 - Alberto Contador", "2010 - Andy Schleck", "2011 - Cadel Evans", "2012 - Bradley Wiggins", "2013 - Chris Froome", "2014 - Vincenzo Nibali", "2015 - Chris Froome", "2016 - Chris Froome"
        );
  }

  @Test
  void winnerNameLengths() {
    List<Integer> mapWinnerNameLengthToList = tdfWinners
        .stream()
        .map(Winner::getName)
        .map(String::length)
        .collect(toList());
    // mapWinnerNameLengthToList [13, 16, 13, 16, 12, 11, 15, 12, 15, 12, 12]
    out.println("mapWinnerNameLengthToList " + mapWinnerNameLengthToList);
    MatcherAssert.assertThat(mapWinnerNameLengthToList,
        contains(13, 16, 13, 16, 12, 11, 15, 12, 15, 12, 12
        ));

    assertThat(mapWinnerNameLengthToList)
        .containsOnly(13, 16, 13, 16, 12, 11, 15, 12, 15, 12, 12);
  }

  @Test
  void anyWinnerNamedWiggins() {
    // matching - allMatch, noneMatch
    Optional<Winner> anyWinnerNamedWiggins = tdfWinners.stream()
        .filter(w -> w.getName().contains("Wiggins"))
        .findAny();
    // winner2012 - Bradley Wiggins
    out.println("winner2012 - " + anyWinnerNamedWiggins.get());
    MatcherAssert.assertThat(anyWinnerNamedWiggins.get().getName(), containsString("Wiggins"));

    assertThat(anyWinnerNamedWiggins).isPresent();
    assertThat(anyWinnerNamedWiggins.get().getName())
        .contains("Wiggins");
  }

  @Test
  void firstWinner() {
    Optional<Integer> firstWinnerOfYear2014 = tdfWinners.stream()
        .map(Winner::getYear)
        .filter(x -> x == 2014)
        .findFirst();
    // winnerYear2014 - 2014
    out.println("winnerYear2014 - " + firstWinnerOfYear2014.get());
    MatcherAssert.assertThat(firstWinnerOfYear2014.get(), equalTo(2014));

    assertThat(firstWinnerOfYear2014)
        .isPresent()
        .hasValue(2014);
  }

  @Test
  void totalDistanceOfAllRaces() {
    // reducing - 0 --> initial value
    int totalDistance = tdfWinners.stream()
        .map(Winner::getLengthKm)
        .reduce(0, Integer::sum);
    // totalDistance - 38767
    out.println("totalDistance - " + totalDistance);
    MatcherAssert.assertThat(totalDistance, equalTo(38767));

    assertThat(totalDistance).isEqualTo(38767);
  }

  @Test
  void yearWithTheShortestRace() {
    Optional<Integer> shortestYear = tdfWinners.stream()
        .map(Winner::getLengthKm)
        .reduce(Integer::min);
    // shortestYear - 3360
    out.println("shortestYear - " + shortestYear.get());
    MatcherAssert.assertThat(shortestYear.get(), equalTo(3360));

    assertThat(shortestYear).isNotEmpty().hasValue(3360);
  }

  @Test
  void yearWithTheLongestRace() {
    Optional<Integer> longestYear = tdfWinners.stream()
        .map(Winner::getLengthKm)
        .reduce(Integer::max);
    // longestYear - 3661

    longestYear.ifPresent(year -> out.println("longestYear - " + year));
    MatcherAssert.assertThat(longestYear.get(), equalTo(3661));

    assertThat(longestYear)
        .isPresent()
        .hasValue(3661);
  }

  @Test
  void winnerWithTheFastestAverageSpeed() {
    Optional<Winner> fastestAveSpeedOfWinner = tdfWinners.stream()
        .min(Comparator.comparingDouble(Winner::getAveSpeed));
    out.println("fastestTDF - " + fastestAveSpeedOfWinner.get());
    MatcherAssert.assertThat(fastestAveSpeedOfWinner.get().getAveSpeed(), closeTo(39, 0));

    assertThat(fastestAveSpeedOfWinner.get().getAveSpeed())
        .isCloseTo(39.0, Offset.offset(0.0001));
  }

  @Test
  void fastestAverageSpeed() {
    // shorthand use mapToDouble
    OptionalDouble fastestTDF = tdfWinners.stream()
        .mapToDouble(Winner::getAveSpeed)
        .min();
    // fastestTDF - 39.0
    out.println("fastestTDF - " + fastestTDF.getAsDouble());
    MatcherAssert.assertThat(fastestTDF.getAsDouble(), closeTo(39, 0));

    assertThat(fastestTDF)
      .isNotEmpty()
      .hasValue(39.0);
  }

  @Test
  void mapByWinnersName() {
    // groupingby - make a map whose keys are names
    Map<String, List<Winner>> namesVsWinner = tdfWinners.stream()
        .collect(groupingBy(Winner::getName));
    // namesVsWinner - {Bradley Wiggins=[Bradley Wiggins], Carlos Sastre=[Carlos Sastre], Cadel Evans=[Cadel Evans], Óscar Pereiro=[Óscar Pereiro], Chris Froome=[Chris Froome, Chris Froome, Chris Froome], Andy Schleck=[Andy Schleck], Alberto Contador=[Alberto Contador, Alberto Contador], Vincenzo Nibali=[Vincenzo Nibali]}
    out.println("namesVsWinner - " + namesVsWinner);
    MatcherAssert.assertThat(namesVsWinner, allOf(
        hasEntry(equalTo("Bradley Wiggins"), hasSize(1)),
        hasEntry(equalTo("Chris Froome"), hasSize(3))
    ));

    assertThat(namesVsWinner)
      .hasEntrySatisfying("Bradley Wiggins", winnerList -> assertThat(winnerList).hasSize(1))
      .hasEntrySatisfying("Carlos Sastre", winnerList -> assertThat(winnerList).hasSize(1))
      .hasEntrySatisfying("Cadel Evans", winnerList -> assertThat(winnerList).hasSize(1))
      .hasEntrySatisfying("Óscar Pereiro", winnerList -> assertThat(winnerList).hasSize(1))
      .hasEntrySatisfying("Chris Froome", winnerList -> assertThat(winnerList).hasSize(3))
      .hasEntrySatisfying("Alberto Contador", winnerList -> assertThat(winnerList).hasSize(2))
      .hasEntrySatisfying("Vincenzo Nibali", winnerList -> assertThat(winnerList).hasSize(1));
  }

  @Test
  void commaSeparatedNames() {
    // join strings
    String allTDFWinnersTeamsCSV = tdfWinners.stream()
        .map(Winner::getTeam)
        .collect(joining(", "));
    // allTDFWinnersTeams Caisse d'Epargne–Illes Balears, Discovery Channel, Team CSC, Astana, Team Saxo Bank, BMC Racing Team, Team Sky, Team Sky, Astana, Team Sky, Team Sky
    out.println("allTDFWinnersTeams " + allTDFWinnersTeamsCSV);
    MatcherAssert.assertThat(allTDFWinnersTeamsCSV, equalTo("Caisse d'Epargne–Illes Balears, Discovery Channel, Team CSC, Astana, Team Saxo Bank, BMC Racing Team, Team Sky, Team Sky, Astana, Team Sky, Team Sky"));
    assertThat(allTDFWinnersTeamsCSV).isEqualTo("Caisse d'Epargne–Illes Balears, Discovery Channel, Team CSC, Astana, Team Saxo Bank, BMC Racing Team, Team Sky, Team Sky, Astana, Team Sky, Team Sky");
  }

  @Test
  void mapByNationality() {
    // grouping
    Map<String, List<Winner>> winnersByNationality = tdfWinners.stream()
        .collect(groupingBy(Winner::getNationality));
    // winnersByNationality - {Great Britain=[Bradley Wiggins, Chris Froome, Chris Froome, Chris Froome], Luxembourg=[Andy Schleck], Italy=[Vincenzo Nibali], Australia=[Cadel Evans], Spain=[Óscar Pereiro, Alberto Contador, Carlos Sastre, Alberto Contador]}
    out.println("winnersByNationality - " + winnersByNationality);
    MatcherAssert.assertThat(winnersByNationality, allOf(
        hasEntry(equalTo("Great Britain"), hasSize(4)),
        hasEntry(equalTo("Spain"), hasSize(4)),
        hasEntry(equalTo("Luxembourg"), hasSize(1)),
        hasEntry(equalTo("Italy"), hasSize(1)),
        hasEntry(equalTo("Australia"), hasSize(1)),
        hasEntry(equalTo("Luxembourg"), hasSize(1))
    ));

    assertThat(winnersByNationality)
      .hasEntrySatisfying("Great Britain", winnerList -> assertThat(winnerList).hasSize(4))
      .hasEntrySatisfying("Spain", winnerList -> assertThat(winnerList).hasSize(4))
      .hasEntrySatisfying("Luxembourg", winnerList -> assertThat(winnerList).hasSize(1))
      .hasEntrySatisfying("Italy", winnerList -> assertThat(winnerList).hasSize(1))
      .hasEntrySatisfying("Australia", winnerList -> assertThat(winnerList).hasSize(1))
      .hasEntrySatisfying("Luxembourg", winnerList -> assertThat(winnerList).hasSize(1));
  }

  @Test
  void winsByNationality() {
    Map<String, Long> winsByNationalityCounting = tdfWinners.stream()
        .collect(groupingBy(Winner::getNationality, counting()));
    // winsByNationalityCounting - {Great Britain=4, Luxembourg=1, Italy=1, Australia=1, Spain=4}
    out.println("winsByNationalityCounting - " + winsByNationalityCounting);
    MatcherAssert.assertThat(winsByNationalityCounting, allOf(
        hasEntry(equalTo("Great Britain"), equalTo(4l)),
        hasEntry(equalTo("Spain"), equalTo(4l)),
        hasEntry(equalTo("Italy"), equalTo(1l)),
        hasEntry(equalTo("Australia"), equalTo(1l)),
        hasEntry(equalTo("Luxembourg"), equalTo(1l))
    ));

    assertThat(winsByNationalityCounting)
      .containsEntry("Great Britain", 4l)
      .containsEntry("Spain", 4l)
      .containsEntry("Luxembourg", 1l)
      .containsEntry("Italy", 1l)
      .containsEntry("Australia", 1l)
      .containsEntry("Luxembourg", 1l);
  }
}