package br.com.uglyson.geojson.model;


import br.com.uglyson.geojson.model.ugly.G;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(use = Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Point.class),
    @JsonSubTypes.Type(value = MultiPoint.class),
    @JsonSubTypes.Type(value = LineString.class),
    @JsonSubTypes.Type(value = MultiLineString.class),
    @JsonSubTypes.Type(value = Polygon.class),
    @JsonSubTypes.Type(value = MultiPolygon.class)
})
public abstract class Geometry<T> extends GeoJsonObject{

  private T coordinates;

  public T getCoordinates() {
    return coordinates;
  }

  public Geometry setCoordinates(T coordinates) {
    this.coordinates = coordinates;
    return this;
  }

  abstract public G uglify();


}
