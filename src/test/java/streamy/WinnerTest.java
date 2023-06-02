package streamy;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.IntStream;

import static java.lang.System.out;
import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.*;
import static streamy.Winner.*;

/**
 * This example code comes from https://dzone.com/articles/a-java-8-streams-cookbook"
 */
class WinnerTest {

  @Test
  void winnersOfToursLessThan3500km() {
    // Filter and Map -
    List<String> winnersOfToursLessThan3500km = tdfWinners.stream()
        .filter(d -> d.lengthKm() < 3500) // Separate out Tours less than 3500km
        .map(Winner::name) // Get names of winners
        .collect(toList()); // Return a list

    // Winners of Tours Less than 3500km - [Alberto Contador, Cadel Evans, Bradley Wiggins, Chris Froome, Chris Froome]
    out.println("Winners of Tours Less than 3500km - " + winnersOfToursLessThan3500km);

    assertThat(winnersOfToursLessThan3500km).containsExactly(
        "Alberto Contador", "Cadel Evans", "Bradley Wiggins", "Chris Froome", "Chris Froome"
    );
  }

  @Test
  void winnersOfToursGreaterThan3500km() {
    List<String> winnersOfToursGreaterThan3500km = tdfWinners.stream()
        .filter(d -> d.lengthKm() >= 3500)
        .map(Winner::name)
        .collect(toList());
    // Winners of Tours Greater than 3500km - [Óscar Pereiro, Alberto Contador, Carlos Sastre, Andy Schleck, Vincenzo Nibali, Chris Froome]

    out.println("Winners of Tours Greater than 3500km - " + winnersOfToursGreaterThan3500km);

    assertThat(winnersOfToursGreaterThan3500km).containsExactly(
        "Óscar Pereiro", "Alberto Contador", "Carlos Sastre", "Andy Schleck", "Vincenzo Nibali", "Chris Froome"
    );
  }

  @Test
  void winnersOfToursGreaterThan3500kmLimit2_Objects() {
    // limit -
    List<Winner> winnerObjectsOfToursLessThan3500kmLimit2 = tdfWinners.stream()
        .filter(d -> d.lengthKm() < 3500)
        .limit(2)
        .collect(toList());

    // winnerObjectsOfToursLessThan3500kmLimit2 [Alberto Contador, Cadel Evans]
    out.println("winnerObjectsOfToursLessThan3500kmLimit2 " + winnerObjectsOfToursLessThan3500kmLimit2);

    assertThat(winnerObjectsOfToursLessThan3500kmLimit2)
        .extracting(Winner::name)
        .containsExactly("Alberto Contador", "Cadel Evans");
  }

  @Test
  void alphabeticWinners() {
    // filter by distinct
    List<String> sortedWinnersNames = tdfWinners
      .stream()
      .map(Winner::name)
      .sorted()
      .collect(toList());

    out.println("sortedWinnersNames - " + sortedWinnersNames);

    assertThat(sortedWinnersNames)
      .containsExactly(
        "Alberto Contador",
        "Alberto Contador",
        "Andy Schleck",
        "Bradley Wiggins",
        "Cadel Evans",
        "Carlos Sastre",
        "Chris Froome",
        "Chris Froome",
        "Chris Froome",
        "Vincenzo Nibali",
        "Óscar Pereiro"
      );
  }

  @Test
  void shortestToLongestRaces() {
    // filter by distinct
    List<String> winnerNamesPlusDistanceSortedByDistance = tdfWinners
      .stream()
      //alternate: .sorted((Winner a, Winner b) -> Integer.compare(a.getLengthKm(), b.getLengthKm()))
      .sorted(Comparator.comparingInt(Winner::lengthKm))
      .map(winner -> winner.name() + ": " + winner.lengthKm())
      .collect(toList());

    out.println("winnerNamesPlusDistanceSortedByDistance - " + winnerNamesPlusDistanceSortedByDistance);

    assertThat(winnerNamesPlusDistanceSortedByDistance)
      .containsExactly(
        "Chris Froome: 3360",
        "Chris Froome: 3404",
        "Cadel Evans: 3430",
        "Alberto Contador: 3459",
        "Bradley Wiggins: 3496",
        "Chris Froome: 3529",
        "Carlos Sastre: 3559",
        "Alberto Contador: 3570",
        "Andy Schleck: 3642",
        "Óscar Pereiro: 3657",
        "Vincenzo Nibali: 3661"
      );
  }

  @Test
  void distinctWinners() {
    // filter by distinct
    List<String> distinctTDFWinners = tdfWinners
        .stream()
        .map(Winner::name)
        .distinct()
        .collect(toList());

    out.println("distinctTDFWinners - " + distinctTDFWinners);

    assertThat(distinctTDFWinners)
      .hasSize(8);
  }

  @Test
  void distinctWinnersCount() {
    long countOfDistinctWinners = tdfWinners
        .stream()
        .map(Winner::name)
        .distinct()
        .count();

    // countOfDistinctWinners - 8
    out.println("countOfDistinctWinners - " + countOfDistinctWinners);

    assertThat(countOfDistinctWinners).isEqualTo(8);
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

    assertThat(skipFirst2Winners)
      .doesNotContain(winner2006, winner2007);
  }

  @Test
  void winnerYearAndName() {
    List<String> yearDashNameList = tdfWinners
        .stream()
        .map(w -> w.year() + " - " + w.name())
        .collect(toList());

    // yearDashNameList [2006 - Óscar Pereiro, 2007 - Alberto Contador, 2008 - Carlos Sastre, 2009 - Alberto Contador, 2010 - Andy Schleck, 2011 - Cadel Evans, 2012 - Bradley Wiggins, 2013 - Chris Froome, 2014 - Vincenzo Nibali, 2015 - Chris Froome, 2016 - Chris Froome]
    out.println("yearDashNameList " + yearDashNameList);

    assertThat(yearDashNameList).
        containsOnly(
            "2006 - Óscar Pereiro", "2007 - Alberto Contador", "2008 - Carlos Sastre", "2009 - Alberto Contador", "2010 - Andy Schleck", "2011 - Cadel Evans", "2012 - Bradley Wiggins", "2013 - Chris Froome", "2014 - Vincenzo Nibali", "2015 - Chris Froome", "2016 - Chris Froome"
        );
  }

  @Test
  void winnerNameLengths() {
    List<Integer> mapWinnerNameLengthToList = tdfWinners
        .stream()
        .map(Winner::name)
        .map(String::length)
        .collect(toList());
    // mapWinnerNameLengthToList [13, 16, 13, 16, 12, 11, 15, 12, 15, 12, 12]
    out.println("mapWinnerNameLengthToList " + mapWinnerNameLengthToList);

    assertThat(mapWinnerNameLengthToList)
        .containsOnly(13, 16, 13, 16, 12, 11, 15, 12, 15, 12, 12);
  }

  @Test
  void anyWinnerNamedWiggins() {
    // matching - allMatch, noneMatch
    Optional<Winner> anyWinnerNamedWiggins = tdfWinners.stream()
        .filter(w -> w.name().contains("Wiggins"))
        .findAny();
    // winner2012 - Bradley Wiggins

    assertThat(anyWinnerNamedWiggins).isPresent();
    assertThat(anyWinnerNamedWiggins.get().name())
        .contains("Wiggins");
  }

  @Test
  void firstWinner() {
    Optional<Winner> firstWinnerOfYear2014 = tdfWinners.stream()
        .filter(winner -> winner.year() == 2014)
        .findFirst();
    // winnerYear2014 - 2014

    assertThat(firstWinnerOfYear2014)
        .isPresent()
        .hasValue(winner2014);
  }

  @Test
  void totalDistanceOfAllRaces() {
    // reducing - 0 --> initial value
    int totalDistance = tdfWinners.stream()
        .map(Winner::lengthKm)
        .reduce(0, Integer::sum);
    // totalDistance - 38767
    out.println("totalDistance - " + totalDistance);

    assertThat(totalDistance).isEqualTo(38767);
  }

  @Test
  void theShortestRace() {
    Optional<Winner> shortestDistance = tdfWinners.stream()
      .min(Comparator.comparingInt(Winner::lengthKm));
      //.collect(Collectors.minBy(Comparator.comparingInt(Winner::getLengthKm)));
        //.map(Winner::getLengthKm)
        //.reduce(Integer::min);
    // shortestYear - 3360

    assertThat(shortestDistance)
      .isPresent().get()
      .extracting(Winner::lengthKm)
      .isEqualTo(3360);
  }

  @Test
  void theLongestRaceDistance() {
    Optional<Integer> longestDistance = tdfWinners.stream()
        .map(Winner::lengthKm)
        .reduce(Integer::max);
    // longestRace - 3661

    longestDistance.ifPresent(year -> out.println("longestRace - " + year));

    assertThat(longestDistance)
        .isPresent()
        .hasValue(3661);
  }

  @Test
  void winnerWithTheFastestAverageSpeed() {
    Optional<Winner> fastestAveSpeedOfWinner = tdfWinners.stream()
        .min(Comparator.comparingDouble(Winner::getAveSpeed));

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

    assertThat(fastestTDF)
      .isNotEmpty()
      .hasValue(39.0);
  }

  @Test
  void yearToWinnersNameMap() {
    Map<Integer, String> year2WinnerMap = tdfWinners.stream()
      .collect(toMap(
        Winner::year,
        Winner::name
      ));

    assertThat(year2WinnerMap)
      .hasSize(11)
      .hasEntrySatisfying(2007, m -> equals("Alberto Contador"));
  }

  @Test
  void mapByWinnersName() {
    // groupingby - make a map whose keys are names
    Map<String, List<Winner>> namesVsWinner = tdfWinners.stream()
        .collect(groupingBy(Winner::name));
    // namesVsWinner - {Bradley Wiggins=[Bradley Wiggins], Carlos Sastre=[Carlos Sastre], Cadel Evans=[Cadel Evans], Óscar Pereiro=[Óscar Pereiro], Chris Froome=[Chris Froome, Chris Froome, Chris Froome], Andy Schleck=[Andy Schleck], Alberto Contador=[Alberto Contador, Alberto Contador], Vincenzo Nibali=[Vincenzo Nibali]}
    out.println("namesVsWinner - " + namesVsWinner);

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
  void commaSeparatedWinningTeams() {
    // join strings
    String allTDFWinnersTeamsCSV = tdfWinners.stream()
        .map(Winner::team)
        .collect(joining(", "));
    // allTDFWinnersTeams Caisse d'Epargne–Illes Balears, Discovery Channel, Team CSC, Astana, Team Saxo Bank, BMC Racing Team, Team Sky, Team Sky, Astana, Team Sky, Team Sky
    out.println("allTDFWinnersTeams " + allTDFWinnersTeamsCSV);
    assertThat(allTDFWinnersTeamsCSV).isEqualTo("Caisse d'Epargne–Illes Balears, Discovery Channel, Team CSC, Astana, Team Saxo Bank, BMC Racing Team, Team Sky, Team Sky, Astana, Team Sky, Team Sky");
  }

  @Test
  void mapByNationality() {
    // grouping
    Map<String, List<Winner>> winnersByNationality = tdfWinners.stream()
        .collect(groupingBy(Winner::nationality));
    // winnersByNationality - {Great Britain=[Bradley Wiggins, Chris Froome, Chris Froome, Chris Froome], Luxembourg=[Andy Schleck], Italy=[Vincenzo Nibali], Australia=[Cadel Evans], Spain=[Óscar Pereiro, Alberto Contador, Carlos Sastre, Alberto Contador]}
    out.println("winnersByNationality - " + winnersByNationality);

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
        .collect(groupingBy(Winner::nationality, counting()));
    // winsByNationalityCounting - {Great Britain=4, Luxembourg=1, Italy=1, Australia=1, Spain=4}
    out.println("winsByNationalityCounting - " + winsByNationalityCounting);

    assertThat(winsByNationalityCounting)
      .containsEntry("Great Britain", 4L)
      .containsEntry("Spain", 4L)
      .containsEntry("Luxembourg", 1L)
      .containsEntry("Italy", 1L)
      .containsEntry("Australia", 1L)
      .containsEntry("Luxembourg", 1L);
  }

  @Test
  public void totalDistance(){
    int resultLengthKm = tdfWinners.stream()
      .map(Winner::lengthKm)
      .mapToInt(Integer::intValue)
      .sum();

    assertThat(resultLengthKm).isEqualTo(38767);
  }

  @Test
  void winnerNameLengthsParallel() {
    IntStream mapWinnerNameLengthToList = tdfWinners.parallelStream()
      .map(Winner::name)
      .mapToInt(String::length);
    // mapWinnerNameLengthToList [13, 16, 13, 16, 12, 11, 15, 12, 15, 12, 12]

    assertThat(mapWinnerNameLengthToList)
      .containsOnly(13, 16, 13, 16, 12, 11, 15, 12, 15, 12, 12);
  }
}