package kr.or.ksmart37.ksmart_mybatis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class KsmartMybatisApplication {

	
	private static final Logger log = LoggerFactory.getLogger(KsmartMybatisApplication.class);

	
	public static void main(String[] args) {
		SpringApplication.run(KsmartMybatisApplication.class, args);
		
	}
	
	//@Scheduled(cron="0/5 * * * * *")
	public void schedulingTest() {
		log.info("배치 테스트");
	}

}
