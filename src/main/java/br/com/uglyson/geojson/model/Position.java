package br.com.uglyson.geojson.model;

import br.com.uglyson.geojson.model.serializer.PositionDeserializer;
import br.com.uglyson.geojson.model.serializer.PositionSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonDeserialize(using = PositionDeserializer.class)
@JsonSerialize(using = PositionSerializer.class)
public class Position {
  private Double lat;
  private Double lng;

  public Double getLat() {
    return lat;
  }

  public Position setLat(Double lat) {
    this.lat = lat;
    return this;
  }

  public Double getLng() {
    return lng;
  }

  public Position setLng(Double lng) {
    this.lng = lng;
    return this;
  }


  public long uglify(){
    return Math.round((180+getLng()) * 100000) * 100000000 + Math.round((90+getLat()) * 100000);
  }

  public static Position prettify(Object o){
    Position position = new Position();

    long l = o instanceof Integer ? ((Integer)o).longValue() : (Long)o;

    double lng = l / 100000000L / 100000.0 - 180;
    double lat = l % 100000000L / 100000.0 - 90;

    return position.setLng(lng).setLat(lat);
  }

}
