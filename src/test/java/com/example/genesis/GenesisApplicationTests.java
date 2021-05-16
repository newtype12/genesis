package com.example.genesis;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import redis.embedded.RedisServer;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class GenesisApplicationTests {

	private static RedisServer redisServer;
	@BeforeAll
	static void startRedis() {
		// https://github.com/kstyrc/embedded-redis/issues/51
		redisServer = RedisServer.builder()
				.port(6379)
				.setting("maxmemory 128M") //maxheap 128M
				.build();

		redisServer.start();
	}


	MockMvc mockMvc;

	@Autowired
	private TestRestTemplate template;




	/**
	 * 登入1 訪客建立單子 自動分配
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception {

		System.out.println(template.getForObject("/v1/user/search/", String.class));
		System.out.println(template.getForObject("/v1/front/order/", String.class));
	}

	/**
	 * 登入
	 * @throws Exception
	 */
	@Test
	public void testInit() throws Exception {

		//模擬使用者1登入
		MultiValueMap<String, Integer> params = new LinkedMultiValueMap<>();
		params.add("id",1);
		template.postForObject("/v1/user/login",params,String.class);
		//模擬使用者2登入
		params.clear();
		params.add("id",2);
		template.postForObject("/v1/user/login",params,String.class);
		//模擬使用者3登入
		params.clear();
		params.add("id",3);
		template.postForObject("/v1/user/login",params,String.class);
		//模擬使用者4登入 (TL)
		params.clear();
		params.add("id",4);
		template.postForObject("/v1/user/login",params,String.class);
		//模擬使用者5登入 (PM)
		params.clear();
		params.add("id",5);
		template.postForObject("/v1/user/login",params,String.class);
		//確認user資料
		System.out.println(template.getForObject("/v1/user/search/", String.class));


	}


	/**
	 * 析构方法之后执行，停止Redis.
	 */
	@AfterAll
	static void stopRedis() {
		redisServer.stop();
	}

}
