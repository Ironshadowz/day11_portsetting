package sg.edu.nus.iss.workshop11;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ch.qos.logback.classic.Logger;

@SpringBootApplication
public class Workshop11Application {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Workshop11Application.class);
	//Default port number
	private static final String DEFAULT_PORT = "3000";
	public static void main(String[] args) 
	{
		logger.info("message");
	//	SpringApplication.run(Workshop11Application.class, args);
		//Initialize spring app
		SpringApplication app = new SpringApplication(Workshop11Application.class);
 
		//Read args array and check for "port" parameter
		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);

		List opsValues = appArgs.getOptionValues("port");
		String portNumber = null;

		//If port number is not in argument
		if(opsValues==null || opsValues.get(0)==null)
		{
			//Read port number from env variables
			portNumber = System.getenv("PORT");
			if(portNumber==null)
			{
				portNumber= DEFAULT_PORT;
			}
		} else
		{
			//Passing port number from Command Line Interface/ command prompt
			portNumber=(String) opsValues.get(0);
		}
		if(portNumber!=null)
		{	
			//Setting port number in the spring-boot config
			app.setDefaultProperties(Collections.singletonMap("server.port", portNumber));
		}
		logger.info(portNumber);
		//Launch spring boot app
		app.run(args);
	}

}
