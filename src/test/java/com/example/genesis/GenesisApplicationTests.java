package com.example.genesis;

import com.example.genesis.controller.UserController;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class GenesisApplicationTests {

	MockMvc mockMvc;

	@Autowired
	private TestRestTemplate template;


	@Sql({"/user_init.sql"})
	@Test
	public void testSearchSync() throws Exception {

		String content = template.getForObject("/v1/user/search/", String.class);
		System.out.println(content	);
	}

}
