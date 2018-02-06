package streamy;

import org.junit.jupiter.api.Test;

import java.util.*;

import static java.util.stream.Collectors.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
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

    System.out.println("Winners of Tours Less than 3500km - " + winnersOfToursLessThan3500km);
    assertThat(winnersOfToursLessThan3500km,
        contains("Alberto Contador", "Cadel Evans", "Bradley Wiggins", "Chris Froome", "Chris Froome"));
  }

  @Test
  void winnersOfToursGreaterThan3500km() {
    List<String> winnersOfToursGreaterThan3500km = tdfWinners
        .stream()
        .filter(d -> d.getLengthKm() >= 3500)
        .map(Winner::getName)
        .collect(toList());
    // Winners of Tours Greater than 3500km - [Óscar Pereiro, Alberto Contador, Carlos Sastre, Andy Schleck, Vincenzo Nibali, Chris Froome]
    System.out.println("Winners of Tours Greater than 3500km - " + winnersOfToursGreaterThan3500km);
    assertThat(winnersOfToursGreaterThan3500km,
        contains("Óscar Pereiro", "Alberto Contador", "Carlos Sastre", "Andy Schleck", "Vincenzo Nibali", "Chris Froome"));
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
    System.out.println("winnerObjectsOfToursLessThan3500kmLimit2 " + winnerObjectsOfToursLessThan3500kmLimit2);
    assertThat(winnerObjectsOfToursLessThan3500kmLimit2,
        contains(
            hasProperty("name", equalTo("Alberto Contador")),
            hasProperty("name", equalTo("Cadel Evans"))
        ));
  }

  @Test
  void distinctWinnders() {
    // filter by distinct
    List<String> distinctTDFWinners = tdfWinners
        .stream()
        .map(Winner::getName)
        .distinct()
        .collect(toList());

    System.out.println("distinctTDFWinners - " + distinctTDFWinners);
    assertThat(distinctTDFWinners,
        hasSize(8));
  }

  @Test
  void distinctWinnersCount() {
    long numberOfDistinceWinners = tdfWinners
        .stream()
        .map(Winner::getName)
        .distinct()
        .count();

    // numberOfDistinceWinners - 8
    System.out.println("numberOfDistinceWinners - " + numberOfDistinceWinners);
    assertThat(numberOfDistinceWinners, equalTo(8L));
  }

  @Test
  void allButTheFirst2Winners() {
    // skip records
    List<Winner> skipFirst2Winners = tdfWinners
        .stream()
        .skip(2)
        .collect(toList());
    // skipEveryOtherTDFWinner - [Carlos Sastre, Alberto Contador, Andy Schleck, Cadel Evans, Bradley Wiggins, Chris Froome, Vincenzo Nibali, Chris Froome, Chris Froome]
    System.out.println("skipEveryOtherTDFWinner - " + skipFirst2Winners);
    assertThat(skipFirst2Winners,
        everyItem(not(in(Arrays.asList(tdfWinners.get(0), tdfWinners.get(1)))))
    );
  }

  @Test
  void winnerYearAndName() {
    List<String> mapWinnerYearNamesToList = tdfWinners
        .stream()
        .map(w -> w.getYear() + " - " + w.getName())
        .collect(toList());

    // mapWinnerYearNamesToList [2006 - Óscar Pereiro, 2007 - Alberto Contador, 2008 - Carlos Sastre, 2009 - Alberto Contador, 2010 - Andy Schleck, 2011 - Cadel Evans, 2012 - Bradley Wiggins, 2013 - Chris Froome, 2014 - Vincenzo Nibali, 2015 - Chris Froome, 2016 - Chris Froome]
    System.out.println("mapWinnerYearNamesToList " + mapWinnerYearNamesToList);
    assertThat(mapWinnerYearNamesToList,
        contains(
            "2006 - Óscar Pereiro", "2007 - Alberto Contador", "2008 - Carlos Sastre", "2009 - Alberto Contador", "2010 - Andy Schleck", "2011 - Cadel Evans", "2012 - Bradley Wiggins", "2013 - Chris Froome", "2014 - Vincenzo Nibali", "2015 - Chris Froome", "2016 - Chris Froome"
        ));
  }

  @Test
  void winnerNameLengths() {
    List<Integer> mapWinnerNameLengthToList = tdfWinners
        .stream()
        .map(Winner::getName)
        .map(String::length)
        .collect(toList());
    // mapWinnerNameLengthToList [13, 16, 13, 16, 12, 11, 15, 12, 15, 12, 12]
    System.out.println("mapWinnerNameLengthToList " + mapWinnerNameLengthToList);
    assertThat(mapWinnerNameLengthToList,
        contains(13, 16, 13, 16, 12, 11, 15, 12, 15, 12, 12
        ));
  }

  @Test
  void anyWinnerNamedWiggins() {
    // matching - allMatch, noneMatch
    Optional<Winner> anyWinnerNamedWiggins = tdfWinners.stream()
        .filter(w -> w.getName().contains("Wiggins"))
        .findAny();
    // winner2012 - Bradley Wiggins
    System.out.println("winner2012 - " + anyWinnerNamedWiggins.get());
    assertThat(anyWinnerNamedWiggins.get().getName(), containsString("Wiggins"));
  }

  @Test
  void firstWinner() {
    Optional<Integer> firstWinnerOfYear2014 = tdfWinners.stream()
        .map(Winner::getYear)
        .filter(x -> x == 2014)
        .findFirst();
    // winnerYear2014 - 2014
    System.out.println("winnerYear2014 - " + firstWinnerOfYear2014.get());
    assertThat(firstWinnerOfYear2014.get(), equalTo(2014));
  }

  @Test
  void totalDistanceOfAllRaces() {
    // reducing - 0 --> initial value
    int totalDistance = tdfWinners.stream()
        .map(Winner::getLengthKm)
        .reduce(0, Integer::sum);
    // totalDistance - 38767
    System.out.println("totalDistance - " + totalDistance);
    assertThat(totalDistance, equalTo(38767));
  }

  @Test
  void yearWithTheShortestRace() {
    Optional<Integer> shortestYear = tdfWinners.stream()
        .map(Winner::getLengthKm)
        .reduce(Integer::min);
    // shortestYear - 3360
    System.out.println("shortestYear - " + shortestYear.get());
    assertThat(shortestYear.get(), equalTo(3360));
  }

  @Test
  void yearWithTheLongestRace() {
    Optional<Integer> longestYear = tdfWinners.stream()
        .map(Winner::getLengthKm)
        .reduce(Integer::max);
    // longestYear - 3661
    System.out.println("longestYear - " + longestYear.get());
    assertThat(longestYear.get(), equalTo(3661));
  }

  @Test
  void winnerWithTheFastestAverageSpeed() {
    Optional<Winner> fastestAveSpeedOfWinner = tdfWinners.stream()
        .min(Comparator.comparingDouble(Winner::getAveSpeed));
    System.out.println("fastestTDF - " + fastestAveSpeedOfWinner.get());
    assertThat(fastestAveSpeedOfWinner.get().getAveSpeed(), closeTo(39, 0));
  }

  @Test
  void fastestAverageSpeed() {
    // shorthand use mapToDouble
    OptionalDouble fastestTDF = tdfWinners.stream()
        .mapToDouble(Winner::getAveSpeed)
        .min();
    // fastestTDF - 39.0
    System.out.println("fastestTDF - " + fastestTDF.getAsDouble());
    assertThat(fastestTDF.getAsDouble(), closeTo(39, 0));
  }

  @Test
  void mapByWinnersName() {
    // groupingby - make a map whose keys are names
    Map<String, List<Winner>> namesVsWinner = tdfWinners.stream()
        .collect(groupingBy(Winner::getName));
    // namesVsWinner - {Bradley Wiggins=[Bradley Wiggins], Carlos Sastre=[Carlos Sastre], Cadel Evans=[Cadel Evans], Óscar Pereiro=[Óscar Pereiro], Chris Froome=[Chris Froome, Chris Froome, Chris Froome], Andy Schleck=[Andy Schleck], Alberto Contador=[Alberto Contador, Alberto Contador], Vincenzo Nibali=[Vincenzo Nibali]}
    System.out.println("namesVsWinner - " + namesVsWinner);
    assertThat(namesVsWinner, allOf(
        hasEntry(equalTo("Bradley Wiggins"), hasSize(1)),
        hasEntry(equalTo("Chris Froome"), hasSize(3))
    ));
  }

  @Test
  void commaSeparatedNames() {
    // join strings
    String allTDFWinnersTeamsCSV = tdfWinners.stream()
        .map(Winner::getTeam)
        .collect(joining(", "));
    // allTDFWinnersTeams Caisse d'Epargne–Illes Balears, Discovery Channel, Team CSC, Astana, Team Saxo Bank, BMC Racing Team, Team Sky, Team Sky, Astana, Team Sky, Team Sky
    System.out.println("allTDFWinnersTeams " + allTDFWinnersTeamsCSV);
    assertThat(allTDFWinnersTeamsCSV, equalTo("Caisse d'Epargne–Illes Balears, Discovery Channel, Team CSC, Astana, Team Saxo Bank, BMC Racing Team, Team Sky, Team Sky, Astana, Team Sky, Team Sky"));
  }

  @Test
  void mapByNationality() {
    // grouping
    Map<String, List<Winner>> winnersByNationality = tdfWinners.stream()
        .collect(groupingBy(Winner::getNationality));
    // winnersByNationality - {Great Britain=[Bradley Wiggins, Chris Froome, Chris Froome, Chris Froome], Luxembourg=[Andy Schleck], Italy=[Vincenzo Nibali], Australia=[Cadel Evans], Spain=[Óscar Pereiro, Alberto Contador, Carlos Sastre, Alberto Contador]}
    System.out.println("winnersByNationality - " + winnersByNationality);
    assertThat(winnersByNationality, allOf(
        hasEntry(equalTo("Great Britain"), hasSize(4)),
        hasEntry(equalTo("Spain"), hasSize(4)),
        hasEntry(equalTo("Luxembourg"), hasSize(1)),
        hasEntry(equalTo("Italy"), hasSize(1)),
        hasEntry(equalTo("Australia"), hasSize(1)),
        hasEntry(equalTo("Luxembourg"), hasSize(1))
    ));
  }

  @Test
  void winsByNationality() {
    Map<String, Long> winsByNationalityCounting = tdfWinners.stream()
        .collect(groupingBy(Winner::getNationality, counting()));
    // winsByNationalityCounting - {Great Britain=4, Luxembourg=1, Italy=1, Australia=1, Spain=4}
    System.out.println("winsByNationalityCounting - " + winsByNationalityCounting);
    assertThat(winsByNationalityCounting, allOf(
        hasEntry(equalTo("Great Britain"), equalTo(4l)),
        hasEntry(equalTo("Spain"), equalTo(4l)),
        hasEntry(equalTo("Italy"), equalTo(1l)),
        hasEntry(equalTo("Australia"), equalTo(1l)),
        hasEntry(equalTo("Luxembourg"), equalTo(1l))
    ));
  }
}