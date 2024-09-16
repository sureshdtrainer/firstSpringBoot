package com.example.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.service.GreetingService;

@SpringBootTest
@AutoConfigureMockMvc
class HelloWorldControllerTest {

	@Autowired
	private HelloWorldController controller;

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private GreetingService mockGreetingService;

	@Test
	void contextLoadsWithControllerSuccess() {
		assertThat(controller).isNotNull();
	}
	
	//
	@Test
	void sayHelloShouldReturnDefaultMessageWithMockData() throws Exception {
		//Mock the response from service layer
		when(mockGreetingService.greet()).thenReturn("Hello Suresh!!!");
		
		this.mockMvc.perform(get("/hello")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello World!")));
	}

	// This is Integration test as it is calling the Service class logic as well to
	// return the message
	@Test
	void sayHelloShouldReturnDefaultMessageFromService() throws Exception {
		this.mockMvc.perform(get("/hello")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Suresh World!")));
	}
}
