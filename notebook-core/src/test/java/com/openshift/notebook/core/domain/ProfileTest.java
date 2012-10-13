package com.openshift.notebook.core.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProfileTest {

	@Test
	public void testToJson() {
		Profile profile = ProfileBuilder.profile().withActive(true)
				.withEmail("test@test.com").withPassword("password").build();
		System.out.println(profile.toJson());
		assertNotNull(profile.toJson());
	}

}
