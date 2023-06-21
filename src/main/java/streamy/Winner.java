package streamy;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

/**
 * This example code comes from https://dzone.com/articles/a-java-8-streams-cookbook
 */
public record Winner(int year, String nationality, String name, String team, int lengthKm, Duration winningTime) {
  static final Winner winner2006 =
    new Winner(2006, "Spain", "Óscar Pereiro", "Caisse d'Epargne–Illes Balears", 3657, Duration.parse("PT89H40M27S"));
  static final Winner winner2007 =
    new Winner(2007, "Spain", "Alberto Contador", "Discovery Channel", 3570, Duration.parse("PT91H00M26S"));
  static final Winner winner2008 =
    new Winner(2008, "Spain", "Carlos Sastre", "Team CSC", 3559, Duration.parse("PT87H52M52S"));
  static final Winner winner2009 =
    new Winner(2009, "Spain", "Alberto Contador", "Astana", 3459, Duration.parse("PT85H48M35S"));
  static final Winner winner2010 =
    new Winner(2010, "Luxembourg", "Andy Schleck", "Team Saxo Bank", 3642, Duration.parse("PT91H59M27S"));
  static final Winner winner2011 =
    new Winner(2011, "Australia", "Cadel Evans", "BMC Racing Team", 3430, Duration.parse("PT86H12M22S"));
  static final Winner winner2012 =
    new Winner(2012, "Great Britain", "Bradley Wiggins", "Team Sky", 3496, Duration.parse("PT87H34M47S"));
  static final Winner winner2013 =
    new Winner(2013, "Great Britain", "Chris Froome", "Team Sky", 3404, Duration.parse("PT83H56M20S"));
  static final Winner winner2014 =
    new Winner(2014, "Italy", "Vincenzo Nibali", "Astana", 3661, Duration.parse("PT89H59M06S"));
  static final Winner winner2015 =
    new Winner(2015, "Great Britain", "Chris Froome", "Team Sky", 3360, Duration.parse("PT84H46M14S"));
  static final Winner winner2016 =
    new Winner(2016, "Great Britain", "Chris Froome", "Team Sky", 3529, Duration.parse("PT89H04M48S"));
  public static final List<Winner> tdfWinners = Arrays.asList(
    winner2006, winner2007, winner2008, winner2009, winner2010, winner2011, winner2012, winner2013, winner2014,
    winner2015, winner2016);

  public double getAveSpeed() {
    return ((double) lengthKm() / ((double) winningTime().getSeconds() / 3600));
  }

  @Override
  public String toString() {
    return name;
  }
}
