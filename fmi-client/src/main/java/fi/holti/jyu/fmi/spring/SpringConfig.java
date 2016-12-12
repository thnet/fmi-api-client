package fi.holti.jyu.fmi.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

// Configuration class to declare spring beans
@Configuration
public class SpringConfig {

	// Wire up a central http requeste template class with appropriate
	// converters
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();

		// Jackson converter for converting to Java objects with default object
		// mapper
		MappingJackson2XmlHttpMessageConverter converter = new MappingJackson2XmlHttpMessageConverter();
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(converter);
		restTemplate.setMessageConverters(converters);
		return restTemplate;
	}
}
