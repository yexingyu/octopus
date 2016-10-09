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
import org.nxstudio.octopus.mybatis.mapper.PredictionMapper;
import org.nxstudio.octopus.mybatis.mapper.RawDataNasdaqCompanyMapper;
import org.nxstudio.octopus.mybatis.mapper.RawDataYahooDividendMapper;
import org.nxstudio.octopus.mybatis.mapper.RawDataYahooHistoricalMapper;
import org.nxstudio.octopus.mybatis.mapper.RawDataYahooMinutelyMapper;
import org.nxstudio.octopus.mybatis.mapper.RawDataYahooQuoteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author TimoYe
 */
@Component
public class SessionFactory {
	private final static Logger			l	= LoggerFactory.getLogger(SessionFactory.class);
	private static SqlSessionFactory	factory;

	private static void create(String driver, String url, String username, String password) {
		final PooledDataSource dataSource = new PooledDataSource(driver, url, username, password);
		final TransactionFactory transactionFactory = new JdbcTransactionFactory();
		final Environment environment = new Environment("development", transactionFactory, dataSource);
		final Configuration configuration = new Configuration(environment);
		configuration.addMapper(RawDataNasdaqCompanyMapper.class);
		configuration.addMapper(RawDataYahooMinutelyMapper.class);
		configuration.addMapper(RawDataYahooDividendMapper.class);
		configuration.addMapper(RawDataYahooQuoteMapper.class);
		configuration.addMapper(RawDataYahooHistoricalMapper.class);
		configuration.addMapper(OctopusSymbolMapper.class);
		configuration.addMapper(PredictionMapper.class);

		final SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		factory = builder.build(configuration);

	}

	public static SqlSessionFactory build(String driver, String url, String username, String password) {
		if (factory == null) {
			create(driver, url, username, password);
		}
		return factory;
	}
}
