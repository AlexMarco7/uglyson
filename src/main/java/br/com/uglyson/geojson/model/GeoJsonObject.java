package br.com.uglyson.geojson.model;


import br.com.uglyson.geojson.model.ugly.GJO;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(use = Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Feature.class),
    @JsonSubTypes.Type(value = FeatureCollection.class),
    @JsonSubTypes.Type(value = GeoJsonObject.class)
})
public abstract class GeoJsonObject {
  abstract public GJO uglify();

}
