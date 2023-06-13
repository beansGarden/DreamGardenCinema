package edu.kh.dgc.common.config;

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
      
      sessionFactoryBean.setTypeAliasesPackage("edu.kh.dgc.user.model.dto, edu.kh.dgc.movie.model.dto, "
      		+ "edu.kh.dgc.qna.model.dto, edu.kh.dgc.admin.model.dto, edu.kh.dgc.notice.model.dto");

      
      //留덉씠諛뷀떚�뒪 �꽕�젙 �뙆�씪 寃쎈줈 吏��젙
      sessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
      
      //SqlSession 媛앹껜 諛섑솚
      return sessionFactoryBean.getObject();
   }

   
   //SqlSessionTemplate : 湲곕낯 SQL �떎�뻾 + �듃�옖�옲�뀡 泥섎━
   @Bean
   public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sessionFactory) {
      return new SqlSessionTemplate(sessionFactory);
   }

   //DataSourceTransactionManager : �듃�옖�옲�뀡 留ㅻ땲��
   @Bean
   public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
      return new DataSourceTransactionManager(dataSource);
   }

}