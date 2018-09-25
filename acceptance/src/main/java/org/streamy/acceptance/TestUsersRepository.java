package org.streamy.acceptance;

import org.springframework.jdbc.core.JdbcTemplate;
import org.streamy.persistence.data.UsersData;
import org.streamy.persistence.repository.UsersRepository;
import org.streamy.service.UsersService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestUsersRepository {
  JdbcTemplate jdbc = DataSourceSetup.getJdbcTemplate();
  UsersRepository userRepository = new UsersRepository(jdbc);
  UsersService usersService = new UsersService(userRepository);
  UsersData userData = null;
  Long id = null;

  @BeforeClass
  public void init(){
    List<UsersData> newList = new ArrayList<>();
   newList.add(new UsersData(null, "streamy", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()),
      "streamy", "Day", "Dream", "day dream streamy", "dayDream@streamy.com", 100000L));
    newList.add(new UsersData(null, "streamy_1", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()),
      "streamy1", "Day", "Dream", "day dream streamy 1", "dayDream@streamy.com", 200000L));
    newList.add(new UsersData(null, "streamy_2", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()),
      "streamy2", "Day", "Dream", "day dream streamy 2", "dayDream@streamy.com", 300000L));
    newList.add(new UsersData(null, "streamy_3", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()),
      "streamy3", "Day", "Dream", "day dream streamy 3", "dayDream@streamy.com", 400000L));

    for(UsersData usersData_ : newList)
      userRepository.create(usersData_);
  }

  @Test
  public void testPeek(){
    Assert.assertEquals(userRepository.findUserById(1001L).getUserId().longValue(), 1001L);
  }

  @Test
  public void testChangeValue(){
    List<UsersData> listUsersData = userRepository.findAll();
    Stream<UsersData> streamUsersData = listUsersData.stream();

    streamUsersData.peek(e -> usersService.salaryIncrement(e, 10L))
      .peek(e -> System.out.println(e.getSalary()))
      .collect(Collectors.toList());
  }

  @Test
  public void testShowValue(){
    List<UsersData> listUsersData = userRepository.findAll();
    Stream<UsersData> streamUsersData = listUsersData.stream();

    streamUsersData.peek(e -> System.out.println(e.getSalary()))
      .collect(Collectors.toList());
  }

  @Test
  public void testStoreValue(){
    List<Long> resultSortedSalary = new ArrayList<>();
    List<UsersData> listUsersData = userRepository.findAll();
    Stream<UsersData> streamUsersData = listUsersData.stream();

    streamUsersData.peek(e -> System.out.println(e.getSalary()))
      .peek(e -> resultSortedSalary.add(e.getSalary()))
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
