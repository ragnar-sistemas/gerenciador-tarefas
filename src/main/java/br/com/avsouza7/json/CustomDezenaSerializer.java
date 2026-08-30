package br.com.avsouza7.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomDezenaSerializer extends JsonSerializer<String> {
	@Override
	public void serialize(String id, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeNumber(Long.valueOf(id));
	}
}
