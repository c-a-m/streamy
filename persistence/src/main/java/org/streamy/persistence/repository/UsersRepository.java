package org.streamy.persistence.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.streamy.persistence.data.UsersData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UsersRepository {
  final private JdbcTemplate jdbc;

  public UsersRepository(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
  }

  public Long create(UsersData userData) {
    String sql ="INSERT INTO USERS (" +
      "USER_ID, " +
      "CONTACT_NAME, " +
      "CREATE_DATE, " +
      "UPDATE_DATE, " +
      "LOGIN_NAME, " +
      "FIRST_NAME, " +
      "LAST_NAME, " +
      "DISPLAY_NAME, " +
      "EMAIL, " +
      "SALARY " +
      ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

    final Long seqUserId = jdbc.queryForObject( "select nextval('SEQ_USERS')", Long.class);

    jdbc.update(sql,
      seqUserId,
      userData.getContactName(),
      userData.getCreateDate(),
      userData.getUpdateDate(),
      userData.getLoginName(),
      userData.getFirstName(),
      userData.getLastName(),
      userData.getDisplayName(),
      userData.getEmail(),
      userData.getSalary()
      );

    return seqUserId;
  }

  public UsersData findUserById(Long userId){
    UsersData userData = new UsersData();
    userData = jdbc.queryForObject("SELECT " +
      "USER_ID, " +
      "CONTACT_NAME, " +
      "CREATE_DATE, " +
      "UPDATE_DATE, " +
      "LOGIN_NAME, " +
      "FIRST_NAME, " +
      "LAST_NAME, " +
      "DISPLAY_NAME, " +
      "EMAIL, " +
      "SALARY " +
      "FROM USERS WHERE USER_ID = ? ", new UserDataMapper(), userId);

    return userData;
  }

  public List<UsersData> findAll(){

    List<UsersData> userData = jdbc.query("SELECT " +
      "USER_ID, " +
      "CONTACT_NAME, " +
      "CREATE_DATE, " +
      "UPDATE_DATE, " +
      "LOGIN_NAME, " +
      "FIRST_NAME, " +
      "LAST_NAME, " +
      "DISPLAY_NAME, " +
      "EMAIL, " +
      "SALARY " +
      "FROM USERS ", new UserDataMapper());

    return userData;
  }

  private static final class UserDataMapper implements RowMapper<UsersData> {
    @Override
    public UsersData mapRow(ResultSet resultSet, int i) throws SQLException {
      UsersData userData = new UsersData();
      userData.setUserId(resultSet.getLong("user_id"));
      userData.setContactName(resultSet.getString("contact_name"));
      userData.setCreateDate(resultSet.getTimestamp("create_date"));
      userData.setUpdateDate(resultSet.getTimestamp("update_date"));
      userData.setLoginName(resultSet.getString("login_name"));
      userData.setFirstName(resultSet.getString("first_name"));
      userData.setLastName(resultSet.getString("last_name"));
      userData.setDisplayName(resultSet.getString("display_name"));
      userData.setEmail(resultSet.getString("email"));
      userData.setSalary(resultSet.getLong("salary"));
      return userData;
    }
  }
}
