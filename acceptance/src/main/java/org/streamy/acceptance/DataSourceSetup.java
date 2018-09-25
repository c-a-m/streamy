package org.streamy.acceptance;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testng.annotations.BeforeSuite;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class DataSourceSetup {

  static DataSource dataSource = DataSourceSetup.loadDataSource();

  @BeforeSuite
  static DataSource loadDataSource() {
    try {
      dataSource = EmbeddedPostgres.start().getPostgresDatabase();
      final Flyway flyway = new Flyway();
      flyway.setLocations("classpath:postgres");
      flyway.setDataSource(dataSource);
      flyway.migrate();
      return dataSource;
    } catch (IOException e) {
      throw new RuntimeException("Could not load postgres", e);
    }
  }

  @Bean
  public static DataSource getDataSource() {
    return dataSource;
  }

  @Bean
  public static JdbcTemplate getJdbcTemplate() {
    return new JdbcTemplate(dataSource);
  }
}
