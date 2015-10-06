package dk.fitfit.mybiz.entities;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

// Credits goes to http://blog.chris-ritchie.com/2014/09/localdate-java-8-custom-serializer.html
public class JsonDateDeserializer extends JsonDeserializer<LocalDate> {

	@Override
	public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException {

		ObjectCodec oc = jp.getCodec();
		TextNode node = oc.readTree(jp);
		String dateString = node.textValue();

		Instant instant = Instant.parse(dateString);
		LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		return dateTime.toLocalDate();
	}
}
