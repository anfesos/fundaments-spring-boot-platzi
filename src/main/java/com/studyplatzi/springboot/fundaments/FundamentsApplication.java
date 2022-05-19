package com.studyplatzi.springboot.fundaments;

import com.studyplatzi.springboot.fundaments.component.ComponentDependency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundamentsApplication implements CommandLineRunner {

	//------
	//Dependency injection
	private ComponentDependency componentDependency;

	@Autowired  //This annotation is not obligatory
	public FundamentsApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency){
		//if you have two class with the same implementacion about interface, you must to use @Qualifier or @Primary
		this.componentDependency = componentDependency;
	}
	//------


	public static void main(String[] args) {
		SpringApplication.run(FundamentsApplication.class, args);
	}

	//Uso de la dependencia
	@Override
	public void run(String... args) throws Exception {
		componentDependency.saludar();
	}
}
