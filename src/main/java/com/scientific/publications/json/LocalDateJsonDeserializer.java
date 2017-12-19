package com.scientific.publications.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Custom JsonDeserializer to {@link LocalDate}
 *
 * @author <a href="mailto:mauricionrgarcia@gmal.com">Mauricio Garcia</a>
 * @version
 * @sinse 14/12/2017 20:25:09
 */
@Component
public class LocalDateJsonDeserializer extends JsonDeserializer<LocalDate> {

	@Autowired
	private transient Environment env;

	@Override
	public LocalDate deserialize(JsonParser json, DeserializationContext context) throws IOException {
		DateTimeFormatter dateTimeFormater = DateTimeFormatter.ofPattern(env.getProperty("spring.jackson.date-format"));
		return LocalDate.parse(json.getValueAsString(), dateTimeFormater);
	}
}