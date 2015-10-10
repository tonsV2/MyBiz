package dk.fitfit.mybiz.entities.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import dk.fitfit.mybiz.resources.ExpenseResource;

import java.io.IOException;

public class JsonResourceSerializer extends JsonSerializer<ExpenseResource> {
	@Override
	public void serialize(final ExpenseResource expenseResource, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
		jsonGenerator.writeObject(expenseResource.getProperties());
	}
}
