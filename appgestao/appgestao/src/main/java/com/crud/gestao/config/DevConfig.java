package com.crud.gestao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.crud.gestao.services.DBService;

@Configuration
@Profile("dev")
public class DevConfig {
	
	@Autowired
	private DBService dbservice;
	
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String value;
	
	//caso a configuração seja create ele cria as tabelas (cuidado, só precisar deixar como create a primeira vez que inicia o projeto,
	//depois tem que mudar para none 
	@Bean
	public boolean  instanciaDB() {
		if(value.equals("create")) {
			this.dbservice.instanciaDB();
		}
		return false;
	}

}
