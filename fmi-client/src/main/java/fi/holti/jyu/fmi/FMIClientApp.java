package fi.holti.jyu.fmi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import fi.holti.jyu.fmi.client.FMIClient;
import fi.holti.jyu.fmi.client.FMIClientImpl;

/**
 * 
 * @author timoh
 *
 */
// Annotate auto configuration, component scanning and this class as a config
// class
// Autoconfiguration tries to guess what Spring beans should we created based on
// classpath.
@SpringBootApplication()
public class FMIClientApp {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(FMIClientApp.class, args);
		FMIClient fmiClient = applicationContext.getBean(FMIClientImpl.class);
		fmiClient.convertFMIXMLToJSONFile();
	}

}
