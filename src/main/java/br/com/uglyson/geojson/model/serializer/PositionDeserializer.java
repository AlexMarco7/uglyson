package br.com.uglyson.geojson.model.serializer;

import br.com.uglyson.geojson.model.Position;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class PositionDeserializer extends JsonDeserializer<Position> {
  @Override
  public Position deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
    ObjectCodec oc = jsonParser.getCodec();
    Double[] doubles = oc.readValue(jsonParser, Double[].class);
    return new Position().setLng(doubles[0]).setLat(doubles[1]);
  }
}
