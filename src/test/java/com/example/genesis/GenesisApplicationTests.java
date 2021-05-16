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
import redis.embedded.RedisServer;

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


	@Sql({"/data.sql"})
	@Test
	public void testInit() throws Exception {

		String content = template.getForObject("/v1/user/search/", String.class);
		System.out.println(content	);
	}


	/**
	 * 析构方法之后执行，停止Redis.
	 */
	@AfterAll
	static void stopRedis() {
		redisServer.stop();
	}

}
