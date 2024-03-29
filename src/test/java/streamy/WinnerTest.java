package streamy;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.System.out;
import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static streamy.UsersData.userList;
import static streamy.Winner.*;


/**
 * This example code comes from https://dzone.com/articles/a-java-8-streams-cookbook
 */
class WinnerTest {
  @Test
  void winnersOfToursLessThan3500km() {
    // Filter and Map -
    List<String> winnersOfToursLessThan3500km = null;
//      tdfWinners
//        .stream()
///        cool streaming code here

    // Winners of Tours Less than 3500km - [Alberto Contador, Cadel Evans, Bradley Wiggins, Chris Froome, Chris Froome]
    out.println("Winners of Tours Less than 3500km - " + winnersOfToursLessThan3500km);

    assertThat(winnersOfToursLessThan3500km).containsExactly(
        "Alberto Contador", "Cadel Evans", "Bradley Wiggins", "Chris Froome", "Chris Froome"
    );
  }

  @Test
  void winnersOfToursGreaterThan3500km() {
    List<String> winnersOfToursGreaterThan3500km = null;
//      tdfWinners
//        .stream()
//        cool streaming code here

    // Winners of Tours Greater than 3500km - [Óscar Pereiro, Alberto Contador, Carlos Sastre, Andy Schleck, Vincenzo Nibali, Chris Froome]

    out.println("Winners of Tours Greater than 3500km - " + winnersOfToursGreaterThan3500km);

    assertThat(winnersOfToursGreaterThan3500km).containsExactly(
        "Óscar Pereiro", "Alberto Contador", "Carlos Sastre", "Andy Schleck", "Vincenzo Nibali", "Chris Froome"
    );
  }

  @Test
  void winnersOfToursGreaterThan3500kmLimit2_Objects() {
    // limit -
    List<Winner> winnerObjectsOfToursLessThan3500kmLimit2 = null;
//      tdfWinners
//        .stream()
//        cool streaming code here

    // winnerObjectsOfToursLessThan3500kmLimit2 [Alberto Contador, Cadel Evans]
    out.println("winnerObjectsOfToursLessThan3500kmLimit2 " + winnerObjectsOfToursLessThan3500kmLimit2);

    assertThat(winnerObjectsOfToursLessThan3500kmLimit2)
        .extracting(Winner::name)
        .containsExactly("Alberto Contador", "Cadel Evans");
  }

  @Test
  void alphabeticWinners() {
    // filter by distinct
    List<String> sortedWinnersNames = null;
//    tdfWinners
//      .stream()
//        cool streaming code here

    out.println("alphabeticWinners - " + sortedWinnersNames);

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
    List<String> winnerNamesPlusDistanceSortedByDistance = null;
//    tdfWinners
//      .stream()
//        cool streaming code here


    out.println("shortestToLongestRaces - " + winnerNamesPlusDistanceSortedByDistance);

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
  public void testReduceSum(){
    OptionalInt sumLengthKm = null;
    //      tdfWinners
    //        .stream()
    //        cool streaming code here

    out.println("distinctTDFWinners - " + sumLengthKm);
    assertEquals(sumLengthKm.getAsInt(), 38767);

  }

  @Test
  void distinctWinners() {
    // filter by distinct
    List<String> distinctTDFWinners = null;
//      tdfWinners
//        .stream()
//        cool streaming code here

    out.println("distinctTDFWinners - " + distinctTDFWinners);

    assertThat(distinctTDFWinners).hasSize(8);
  }

  @Test
  void distinctWinnersCount() {
    long countOfDistinctWinners = 0;
//      tdfWinners
//        .stream()
//        cool streaming code here

    // numberOfDistinceWinners - 8
    out.println("numberOfDistinceWinners - " + countOfDistinctWinners);

    assertThat(countOfDistinctWinners).isEqualTo(8);
  }

  @Test
  void allButTheFirst2Winners() {
    // skip records
    List<Winner> skipFirst2Winners = null ;
//      tdfWinners
//        .stream()
//        cool streaming code here

    // skipEveryOtherTDFWinner - [Carlos Sastre, Alberto Contador, Andy Schleck, Cadel Evans, Bradley Wiggins, Chris Froome, Vincenzo Nibali, Chris Froome, Chris Froome]
    out.println("skipEveryOtherTDFWinner - " + skipFirst2Winners);

    assertThat(skipFirst2Winners)
      .doesNotContain(winner2006, winner2007);
  }

  @Test
  void winnerYearAndName() {
    List<String> yearDashNameList = null;
//      tdfWinners
//        .stream()
//        cool streaming code here

    // mapWinnerYearNamesToList [2006 - Óscar Pereiro, 2007 - Alberto Contador, 2008 - Carlos Sastre, 2009 - Alberto Contador, 2010 - Andy Schleck, 2011 - Cadel Evans, 2012 - Bradley Wiggins, 2013 - Chris Froome, 2014 - Vincenzo Nibali, 2015 - Chris Froome, 2016 - Chris Froome]
    out.println("mapWinnerYearNamesToList " + yearDashNameList);

    assertThat(yearDashNameList).
        containsOnly(
            "2006 - Óscar Pereiro", "2007 - Alberto Contador", "2008 - Carlos Sastre", "2009 - Alberto Contador", "2010 - Andy Schleck", "2011 - Cadel Evans", "2012 - Bradley Wiggins", "2013 - Chris Froome", "2014 - Vincenzo Nibali", "2015 - Chris Froome", "2016 - Chris Froome"
        );
  }

  @Test
  void winnerNameLengths() {
    List<Integer> mapWinnerNameLengthToList = null;
//      tdfWinners
//        .stream()
//        cool streaming code here

    // mapWinnerNameLengthToList [13, 16, 13, 16, 12, 11, 15, 12, 15, 12, 12]
    out.println("mapWinnerNameLengthToList " + mapWinnerNameLengthToList);

    assertThat(mapWinnerNameLengthToList)
        .containsOnly(13, 16, 13, 16, 12, 11, 15, 12, 15, 12, 12);
  }

  @Test
  void anyWinnerNamedWiggins() {
    // matching - allMatch, noneMatch
    Optional<Winner> anyWinnerNamedWiggins = null;
//      tdfWinners
//        .stream()
//        cool streaming code here

    // winner2012 - Bradley Wiggins

    assertThat(anyWinnerNamedWiggins).isPresent();
    assertThat(anyWinnerNamedWiggins.get().name())
        .contains("Wiggins");
  }

  @Test
  void firstWinner() {
    Optional<Integer> firstWinnerOfYear2014 = null;
//      tdfWinners
//        .stream()
//        cool streaming code here

    // winnerYear2014 - 2014
    out.println("winnerYear2014 - " + firstWinnerOfYear2014.get());

    assertThat(firstWinnerOfYear2014)
        .isPresent()
        .hasValue(2014);
  }

  @Test
  void totalDistanceOfAllRaces() {
    // reducing - 0 --> initial value
    int totalDistance = 0;
//      tdfWinners
//        .stream()
//        cool streaming code here

    // totalDistance - 38767
    out.println("totalDistance - " + totalDistance);

    assertThat(totalDistance).isEqualTo(38767);
  }

  @Test
  void theShortestRace() {
    Optional<Integer> shortestDistance = null;
      //tdfWinners.stream()

    // shortestYear - 3360
    out.println("shortestRace - " + shortestDistance.get());

    assertThat(shortestDistance).isNotEmpty().hasValue(3360);
  }

  @Test
  void theLongestRaceDistance() {
    Optional<Integer> longestDistance = null;
      //tdfWinners.stream()

    // longestRace - 3661
    longestDistance.ifPresent(year -> out.println("longestRace - " + year));

    assertThat(longestDistance)
      .isPresent()
      .hasValue(3661);
  }

  @Test
  void winnerWithTheFastestAverageSpeed() {
    Optional<Winner> fastestAveSpeedOfWinner = null;
//      tdfWinners
//        .stream()
//        cool streaming code here

    assertThat(fastestAveSpeedOfWinner.get().getAveSpeed())
        .isCloseTo(39.0, Offset.offset(0.0001));
  }

  @Test
  void fastestAverageSpeed() {
    // shorthand use mapToDouble
    OptionalDouble fastestTDF = null;
//      tdfWinners
//        .stream()
//        cool streaming code here

    // fastestTDF - 39.0
    out.println("fastestTDF - " + fastestTDF.getAsDouble());

    assertThat(fastestTDF)
      .isNotEmpty()
      .hasValue(39.0);
  }

  @Test
  void yearToWinnersNameMap() {
    Map<Integer, String> year2WinnerMap = null;
      //tdfWinners.stream()

    assertThat(year2WinnerMap)
      .hasSize(11)
      .hasEntrySatisfying(2007, m -> equals("Alberto Contador"));
  }

  @Test
  void mapByWinnersName() {
    // groupingby - make a map whose keys are names
    Map<String, List<Winner>> namesVsWinner = null;
//      tdfWinners
//        .stream()
//        cool streaming code here

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
    String allTDFWinnersTeamsCSV = null;
//      tdfWinners
//        .stream()
//        cool streaming code here

    // allTDFWinnersTeams Caisse d'Epargne–Illes Balears, Discovery Channel, Team CSC, Astana, Team Saxo Bank, BMC Racing Team, Team Sky, Team Sky, Astana, Team Sky, Team Sky
    out.println("allTDFWinnersTeams " + allTDFWinnersTeamsCSV);
    assertThat(allTDFWinnersTeamsCSV).isEqualTo("Caisse d'Epargne–Illes Balears, Discovery Channel, Team CSC, Astana, Team Saxo Bank, BMC Racing Team, Team Sky, Team Sky, Astana, Team Sky, Team Sky");
  }

  @Test
  void mapByNationality() {
    // grouping
    Map<String, List<Winner>> winnersByNationality = null;
//      tdfWinners
//        .stream()
//        cool streaming code here

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
    Map<String, Long> winsByNationalityCounting = null;
//      tdfWinners
//        .stream()
//        cool streaming code here

    // winsByNationalityCounting - {Great Britain=4, Luxembourg=1, Italy=1, Australia=1, Spain=4}
    out.println("winsByNationalityCounting - " + winsByNationalityCounting);

    assertThat(winsByNationalityCounting)
      .containsEntry("Great Britain", 4l)
      .containsEntry("Spain", 4l)
      .containsEntry("Luxembourg", 1l)
      .containsEntry("Italy", 1l)
      .containsEntry("Australia", 1l)
      .containsEntry("Luxembourg", 1l);
  }
}