package com.openshift.notebook.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;

public interface MongoDbFactoryConfig {

	@Bean
	public abstract MongoDbFactory mongoDbFactory() throws Exception;

}