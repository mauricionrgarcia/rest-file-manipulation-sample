package com.scientific.publications.json;

import java.time.LocalDate;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 *
 * Custom serialization to {@link LocalDate}
 *
 * @author <a href="mailto:mauricionrgarcia@gmal.com">Mauricio Garcia</a>
 * @version
 * @sinse 14/12/2017 20:25:09
 */
/**
 * @author <a href="mailto:"></a>
 * @version
 * @sinse 18/12/2017 21:00:35
 */
@Component
@Configuration
public class JacksonDatabindConfiguration {

	/**
	 * {@link JsonDeserializer}
	 */
	@Autowired
	public LocalDateJsonDeserializer localDateJsonDeserializer;

	/**
	 * {@link JsonSerializer}
	 */
	@Autowired
	public LocalDateJsonSerializer localDateJsonSerializer;

	/**
	 * @return ObjectMapper serialization to {@link LocalDate}
	 */
	public ObjectMapper newInstance() {
		ObjectMapper objectMapper = new ObjectMapper();
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		javaTimeModule.addSerializer(LocalDate.class, localDateJsonSerializer);
		javaTimeModule.addDeserializer(LocalDate.class, localDateJsonDeserializer);
		objectMapper.registerModule(javaTimeModule);
		return objectMapper;
	}
}
