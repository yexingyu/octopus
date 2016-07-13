/**
 *
 */
package org.nxstudio.octopus.temp;

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

/**
 * @author TimoYe
 */
public class DbConn {
	private static SqlSessionFactory factory;

	private static void create() {
		PooledDataSource dataSource = new PooledDataSource();
		dataSource.setDriver("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://ec2-52-33-83-5.us-west-2.compute.amazonaws.com:3306/?useSSL=true");
		dataSource.setUsername("nxstudio");
		dataSource.setPassword("nxstudio");

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

	public static SqlSessionFactory build() {
		if (factory == null) {
			create();
		}
		return factory;
	}
}
