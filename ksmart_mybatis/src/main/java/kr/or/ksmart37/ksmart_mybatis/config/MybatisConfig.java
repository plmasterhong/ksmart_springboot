package kr.or.ksmart37.ksmart_mybatis.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "kr.or.ksmart37.ksmart_mybatis.mapper", sqlSessionFactoryRef = "mybatisSqlSessionFactory")
public class MybatisConfig {
	
	@Bean(name="mybatisSqlSessionFactory")
	public SqlSessionFactory mybatisSqlSessionFactory(DataSource dataSource, ApplicationContext context) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setMapperLocations(context.getResources("classpath:/mapper/**/*.xml"));
		sqlSessionFactoryBean.setTypeAliasesPackage("kr.or.ksmart37.ksmart_mybatis.dto");
		
		return sqlSessionFactoryBean.getObject();
	}
}
