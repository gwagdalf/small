package com.small;

import com.small.domain.Item;
import com.small.repository.ItemRepository;
//import com.small.utils.MyHeaderArgumentResolver;
//import com.small.utils.MyInterceptor;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/*
아규먼트 리졸버 작성 순서
1. MyHeaderArgumentResolver 클래스를 작성
2. WebMvcConfigurer를 구현한 java config클래스 작성
3. addArgumentResolvers 메소드 오버라이딩 후 1에서 작성한 클래스 등록
4. controller메소드에서 MyHeader를 파라미터로 적어주면 자동으로 객체가 주입된다.
 */

@EnableCircuitBreaker
@EnableFeignClients
@SpringBootApplication
public class SmallApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(SmallApplication.class, args);
	}

//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new MyInterceptor());
//	}
//
//	@Override
//	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
//		resolvers.add(new MyHeaderArgumentResolver());
//	}


	@Autowired
	ItemRepository itemRepository;

	/**
	 * 최초 로드시, 테스트 데이터 넣는 로직
	 * @return
	 */
	@Bean
	CommandLineRunner cmd() {
		return args -> {
			for(int i=0;i<20;i++){

				Random random = new Random();

				Item item = new Item();
				item.setCatalogId(1100L);
				item.setCurrency("KRW");
				item.setIsActive(true);
				item.setDescription("상세설명 테스트상세설명 테스트상세설명 테스트상세설명 테스트상세설명 테스트");
				item.setName(String.format("상품명 - %d", random.nextInt(10)));
				item.setQuantity(random.nextInt(9));
				item.setPrice(new BigDecimal(i*1000));
				item.setCreatedAt(new Timestamp(System.currentTimeMillis()));
				item.setLastModified(new Timestamp(System.currentTimeMillis()));
				item.setLastModifiedBy("test4plan");
				item.setCreatedBy("test4plan");
				itemRepository.save(item);
			}

		};
	}
}
