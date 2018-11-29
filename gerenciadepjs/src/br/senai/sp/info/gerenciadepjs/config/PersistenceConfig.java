package br.senai.sp.info.gerenciadepjs.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

	@Configuration
	@EnableTransactionManagement
	public class PersistenceConfig {
		@Bean
		public DataSource getDataSource() {
			BasicDataSource dataSource = new BasicDataSource();
			dataSource.setDriverClassName("com.mysql.jdbc.Driver");
			                                             //Nome da database
			dataSource.setUrl("jdbc:mysql://localhost:3306/gerenciadepjs_brq?serverTimezone=UTC");
			dataSource.setUsername("root");
			dataSource.setPassword("Senai@132");
			
			return dataSource;
		}
		
		public Properties getHibernateProperties() {
			Properties properties = new Properties();
			properties.setProperty("hibernate.show_sql", "true");
			//update, create, create-drop, drop
			properties.setProperty("hibernate.hbm2ddl.auto", "update");
			properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
			properties.setProperty("hibernate.connection.characterEncoding", "utf8mb4");
			
			return properties;
		}
		
		@Bean
		public LocalSessionFactoryBean getSessionFactory() {
			LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
			sessionFactory.setDataSource(getDataSource());
			sessionFactory.setHibernateProperties(getHibernateProperties());
			sessionFactory.setPackagesToScan("br.senai.sp.info.gerenciadepjs.model");
		 
			return sessionFactory;
		}

		@Bean
		@Autowired
		public HibernateTransactionManager getTransactionManager() {
			HibernateTransactionManager transactionManager = new HibernateTransactionManager();
			transactionManager.setSessionFactory(getSessionFactory().getObject());
			
			return transactionManager;
		}
	}	
