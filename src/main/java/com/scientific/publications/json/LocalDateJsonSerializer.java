package com.scientific.publications.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Custom JsonSerializer to {@link LocalDate}
 *
 * @author <a href="mailto:mauricionrgarcia@gmal.com">Mauricio Garcia</a>
 * @version
 * @sinse 14/12/2017 20:25:09
 */
@Component
public class LocalDateJsonSerializer extends JsonSerializer<LocalDate> {

	@Autowired
	private transient Environment env;

	@Override
	public void serialize(LocalDate date, JsonGenerator generator, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		DateTimeFormatter dateTimeFormater = DateTimeFormatter.ofPattern(env.getProperty("spring.jackson.date-format"));
		generator.writeString(date.format(dateTimeFormater));
	}
}