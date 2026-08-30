package br.com.avsouza7.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomDezenaDeserializer extends JsonDeserializer<String> {

	@Override
	public String deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext) throws IOException {
		return String.valueOf(jsonparser.getLongValue());
	}

}
