package dk.fitfit.mybiz.entities;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class JsonDateSerializer extends JsonSerializer<LocalDate> {

	@Override
	public void serialize(LocalDate date, JsonGenerator generator, SerializerProvider provider) throws IOException {
		generator.writeNumber(toDate(date).getTime());
	}

	private Date toDate(final LocalDate localDate) {
		final Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		return Date.from(instant);
	}
}
