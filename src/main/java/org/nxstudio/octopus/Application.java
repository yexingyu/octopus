/**
 *
 */
package org.nxstudio.octopus;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.ibatis.session.SqlSessionFactory;
import org.nxstudio.octopus.mybatis.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author TimoYe
 */
@SpringBootApplication
@EnableScheduling
public class Application {
	private final static Logger	l	= LoggerFactory.getLogger(Application.class);
	@Autowired
	private Environment			env;

	@Bean
	public SqlSessionFactory getSqlSessionFactory() {
		final String driver = env.getProperty("octopus.database.driver");
		final String url = env.getProperty("octopus.database.url");
		final String username = env.getProperty("octopus.database.username");
		final String password = env.getProperty("octopus.database.password");
		return SessionFactory.build(driver, url, username, password);
	}

	@Bean(destroyMethod = "shutdown")
	public Executor taskScheduler() {
		return Executors.newScheduledThreadPool(8);
	}

	/**
	 * main
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args).registerShutdownHook();
	}

}
