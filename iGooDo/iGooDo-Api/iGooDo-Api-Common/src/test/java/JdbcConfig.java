import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

//@Configuration
public class JdbcConfig {

  /*配置C3P0数据库连接池*/
  @Bean
  public ComboPooledDataSource dataSource() throws PropertyVetoException {
    ComboPooledDataSource ds = new ComboPooledDataSource();
    ds.setDriverClass("com.mysql.jdbc.Driver");
    ds.setJdbcUrl("jdbc:mysql://111.230.128.214:3306/iGooDo");
    ds.setUser("dev");
    ds.setPassword("dev123456");
    ds.setInitialPoolSize(5);
    ds.setMaxPoolSize(10);
    ds.setMinPoolSize(3);
    ds.setMaxIdleTime(600);
    return ds;
  }
  
  @Bean
  public JdbcTemplate jdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

  @Bean
  public PlatformTransactionManager transactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }
/*
  @Bean
  public JdbcUserRepository jdbcUserRepository(JdbcTemplate jdbcTemplate){
    return new JdbcUserRepository(jdbcTemplate);
  }*/

}
