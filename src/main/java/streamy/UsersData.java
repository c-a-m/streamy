package streamy;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UsersData {
  public static final List<UsersData> userList = Arrays.asList(
   new UsersData(1000L, "streamy", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()),
    "streamy", "Day", "Dream", "day dream streamy", "dayDream@streamy.com", 100000L),
    new UsersData(1001L, "streamy_1", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()),
    "streamy1", "Day", "Dream", "day dream streamy 1", "dayDream@streamy.com", 200000L),
    new UsersData(1002L, "streamy_2", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()),
    "streamy2", "Day", "Dream", "day dream streamy 2", "dayDream@streamy.com", 300000L),
    new UsersData(1003L, "streamy_3", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()),
    "streamy3", "Day", "Dream", "day dream streamy 3", "dayDream@streamy.com", 40000L));

  private Long userId;
  private String contactName;
  private Timestamp createDate;
  private Timestamp updateDate;
  private String loginName;
  private String firstName;
  private String lastName;
  private String displayName;
  private String email;
  private Long salary;

  public UsersData(){}

  public UsersData(Long userId, String contactName, Timestamp createDate, Timestamp updateDate, String loginName, String firstName, String lastName, String displayName, String email, Long salary){
    this.userId = userId;
    this.contactName = contactName;
    this.createDate = createDate;
    this.updateDate = updateDate;
    this.loginName = loginName;
    this.firstName = firstName;
    this.lastName = lastName;
    this.displayName = displayName;
    this.email = email;
    this.salary = salary;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getContactName() {
    return contactName;
  }

  public void setContactName(String contactName) {
    this.contactName = contactName;
  }

  public Timestamp getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Timestamp createDate) {
    this.createDate = createDate;
  }

  public Timestamp getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Timestamp updateDate) {
    this.updateDate = updateDate;
  }

  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Long getSalary() {
    return salary;
  }

  public void setSalary(Long salary) {
    this.salary = salary;
  }

  public Long salaryIncrement(UsersData usersData, Long moreMonies){
    usersData.setSalary(usersData.getSalary() + moreMonies);
    return usersData.getSalary();
  }
}
