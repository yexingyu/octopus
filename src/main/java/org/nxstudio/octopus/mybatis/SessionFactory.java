/**
 *
 */
package org.nxstudio.octopus.mybatis;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.nxstudio.octopus.mybatis.mapper.OctopusSymbolMapper;
import org.nxstudio.octopus.mybatis.mapper.RawDataNasdaqCompanyMapper;
import org.nxstudio.octopus.mybatis.mapper.RawDataYahooDividendMapper;
import org.nxstudio.octopus.mybatis.mapper.RawDataYahooHistoricalMapper;
import org.nxstudio.octopus.mybatis.mapper.RawDataYahooMinutelyMapper;
import org.nxstudio.octopus.mybatis.mapper.RawDataYahooQuoteMapper;
import org.springframework.stereotype.Component;

/**
 * @author TimoYe
 */
@Component
public class SessionFactory {
	private static SqlSessionFactory factory;

	private static void create(String driver, String url, String username, String password) {
		PooledDataSource dataSource = new PooledDataSource(driver, url, username, password);
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		Environment environment = new Environment("development", transactionFactory, dataSource);
		Configuration configuration = new Configuration(environment);
		configuration.addMapper(RawDataNasdaqCompanyMapper.class);
		configuration.addMapper(RawDataYahooMinutelyMapper.class);
		configuration.addMapper(RawDataYahooDividendMapper.class);
		configuration.addMapper(RawDataYahooQuoteMapper.class);
		configuration.addMapper(RawDataYahooHistoricalMapper.class);
		configuration.addMapper(OctopusSymbolMapper.class);

		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		factory = builder.build(configuration);
	}

	public static SqlSessionFactory build(String driver, String url, String username, String password) {
		if (factory == null) {
			create(driver, url, username, password);
		}
		return factory;
	}
}
