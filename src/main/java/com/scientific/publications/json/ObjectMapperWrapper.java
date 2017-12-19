package com.scientific.publications.json;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * Wrapper of custom serialization
 *
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 17/12/2017 19:03:56
 */
@Component
public class ObjectMapperWrapper extends ObjectMapper {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3151500845185502695L;

	/**
	 * {@link LocalDate} @see {@link JacksonDatabindConfiguration}
	 */
	@Autowired
	private JacksonDatabindConfiguration jacksonDatabind;

	/**
	 * @return newInstance
	 */
	public ObjectMapper newInstance() {
		return jacksonDatabind.newInstance();
	}

	@Override
	public <T> T readValue(String content, Class<T> valueType)
			throws IOException, JsonParseException, JsonMappingException {
		return this.newInstance().readValue(content, valueType);
	}
}
