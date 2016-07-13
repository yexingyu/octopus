package org.nxstudio.octopus.temp;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.nxstudio.octopus.mybatis.mapper.RawDataYahooMinutelyMapper;
import org.nxstudio.octopus.mybatis.model.RawDataYahooMinutely;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class Dump {

	public static void main(String[] args) throws Exception {

		String dir = "D:\\Cloud\\Dropbox\\NXStudio\\Stocks Data\\minutely\\Netflix";

		final SqlSession session = DbConn.build().openSession(ExecutorType.BATCH, false);
		final RawDataYahooMinutelyMapper mapper = session.getMapper(RawDataYahooMinutelyMapper.class);

		for (File file : Files.fileTreeTraverser().preOrderTraversal(new File(dir))) {
			if (file.isFile()) {
				System.out.println(file);
				for (String line : Files.readLines(file, Charsets.ISO_8859_1)) {
					if (!StringUtils.startsWith(line, "values:")) {
						System.out.println(line);
						RawDataYahooMinutely object = new RawDataYahooMinutely("NFLX", line);
						mapper.insert(object);
					}
				}
			}
		}
		session.commit();
		session.close();

		System.out.println("Done.");
	}

}
