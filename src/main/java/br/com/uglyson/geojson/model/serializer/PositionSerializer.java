package br.com.uglyson.geojson.model.serializer;

import br.com.uglyson.geojson.model.Position;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class PositionSerializer extends JsonSerializer<Position> {
    @Override
    public void serialize(Position value, JsonGenerator jgen, SerializerProvider provider)
        throws IOException, JsonProcessingException {
      jgen.writeStartArray(2);
      jgen.writeNumber(value.getLng());
      jgen.writeNumber(value.getLat());
      jgen.writeEndArray();
    }


}
