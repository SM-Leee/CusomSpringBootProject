package com.example.springtest.bean;

import com.example.springtest.annotation.NeosBeanMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.example.springtest.**.mapper"}, annotationClass = NeosBeanMapper.class, sqlSessionFactoryRef = "neossqlSessionFactory")
public class dbNeosBean {

    @Bean(name="neosDataSource")
	@ConfigurationProperties(prefix="spring.neos.datasource")
	public DataSource neosDataSource() {
		//application.properties에서 정의한 DB 연결 정보를 빌드
		return DataSourceBuilder.create().build();
	}
    @Bean(name = "neossqlSessionFactory")
    public SqlSessionFactory neossqlSessionFactory(@Qualifier("neosDataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath:neosDb/**/*.xml"));//Mybatis xml 경로
        return sessionFactory.getObject();
    }

    @Bean(name = "neossqlSessionTemplate")
    public SqlSessionTemplate neossqlSessionTemplate(@Qualifier("neossqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        final SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }
}
