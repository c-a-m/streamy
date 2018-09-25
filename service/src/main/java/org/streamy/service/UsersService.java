package org.streamy.service;

import org.streamy.persistence.data.UsersData;
import org.streamy.persistence.repository.UsersRepository;

public class UsersService {
  private final UsersRepository usersRepository;

  public UsersService(UsersRepository usersRepository){
    this.usersRepository = usersRepository;
  }

  public Long salaryIncrement(UsersData usersData, Long moreMonies){
    usersData.setSalary(usersData.getSalary() + moreMonies);
    return usersData.getSalary();
  }
}
