package streamy;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static streamy.UsersData.userList;

public class PeekTest {
  @Test
  public void testPeek(){
    Stream<UsersData> streamUsersData = userList.stream();

    streamUsersData.peek(e -> System.out.println(e.getSalary()))
      .collect(Collectors.toList());
  }

  @Test
  public void testChangeValue(){
    Stream<UsersData> streamUsersData = userList.stream();

    streamUsersData.peek(e -> e.salaryIncrement(e, 10L))
      .peek(e -> System.out.println(e.getSalary()))
      .collect(Collectors.toList());
  }

  @Test
  public void testShowValue(){
    Stream<UsersData> streamUsersData = userList.stream();

    streamUsersData.peek(e -> System.out.println(e.getSalary()))
      .collect(Collectors.toList());
  }

  @Test
  public void testStoreValue(){
    List<Long> resultSortedSalary = new ArrayList<>();
    Stream<UsersData> streamUsersData = userList.stream();

    streamUsersData.map(e -> resultSortedSalary.add(e.getSalary()))
      .peek(e -> System.out.println(resultSortedSalary))
      .collect(Collectors.toList());
  }

  @Test
  public void testNumberGenerator(){
    Stream.iterate(1, (Integer n) -> n + 1)
      .peek(n -> System.out.println("number generated: - " + n))
      .filter(n -> (n % 2 == 0))
      .peek(n -> System.out.println("Even number filter passed for - " + n))
      .limit(5)
      .count();
  }
}
