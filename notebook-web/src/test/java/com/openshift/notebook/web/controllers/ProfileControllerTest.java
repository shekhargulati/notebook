package com.openshift.notebook.web.controllers;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.openshift.notebook.core.domain.Profile;
import com.openshift.notebook.core.domain.ProfileBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = WebContextLoader.class, locations = {
		"classpath:test-webmvc-config.xml", "classpath:applicationContext.xml" })
@ActiveProfiles("in-memory")
public class ProfileControllerTest {

	@Autowired
	private WebApplicationContext wac;

	@Test
	public void testCRUDForProfile() throws Exception {
		Profile profile = ProfileBuilder.profile().withActive(true)
				.withEmail("test@gmail.com").withPassword("password").build();
		profile.setId(1L);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		MockMvc mvc = MockMvcBuilders.webApplicationContextSetup(wac).build();
		String json = profile.toJson();
		mvc.perform(post("/profiles").headers(headers).body(json.getBytes()))
				.andExpect(status().isCreated());

		mvc.perform(get("/profiles/{id}", 1).headers(headers))
				.andExpect(status().isOk())
				.andExpect(content().type("application/json; charset=utf-8"))
				.andExpect(content().string(json));

		profile.setEmail("test1@gmail.com");

		String updatedProfileJson = profile.toJson();
		mvc.perform(
				put("/profiles/{id}", 1).headers(headers).body(
						updatedProfileJson.getBytes()))
				.andExpect(status().isOk())
				.andExpect(content().type("application/json"))
				.andExpect(content().string(updatedProfileJson));

		mvc.perform(delete("/profiles/{id}", 1).headers(headers)).andExpect(
				status().isOk());

		mvc.perform(get("/profiles/{id}", 1).headers(headers)).andExpect(
				status().isNotFound());

	}

}
