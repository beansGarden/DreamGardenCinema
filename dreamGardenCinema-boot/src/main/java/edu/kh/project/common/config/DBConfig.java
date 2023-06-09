package edu.kh.project.common.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration 
@PropertySource("classpath:/application.properties")
public class DBConfig {

    @Autowired
    private ApplicationContext applicationContext; 

   @Bean
   @ConfigurationProperties(prefix = "spring.datasource.hikari")
   public HikariConfig hikariConfig() {
      return new HikariConfig();
   }

   @Bean
   public DataSource dataSource(HikariConfig config) {
      DataSource dataSource = new HikariDataSource(config);
      return dataSource;
   }

   @Bean
   public SqlSessionFactory sessionFactory(DataSource dataSource) throws Exception {
      
      SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
      sessionFactoryBean.setDataSource(dataSource);
      
      //매퍼 파일이 모여있는 경로 지정
      sessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mappers/**.xml"));
      
//      sessionFactoryBean.setTypeAliasesPackage("edu.kh.project.member.model.dto");
      
      //마이바티스 설정 파일 경로 지정
      sessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
      
      //SqlSession 객체 반환
      return sessionFactoryBean.getObject();
   }

   
   //SqlSessionTemplate : 기본 SQL 실행 + 트랜잭션 처리
   @Bean
   public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sessionFactory) {
      return new SqlSessionTemplate(sessionFactory);
   }

   //DataSourceTransactionManager : 트랜잭션 매니저
   @Bean
   public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
      return new DataSourceTransactionManager(dataSource);
   }

}