package br.com.uglyson.geojson.model.ugly;

import br.com.uglyson.geojson.model.GeoJsonObject;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "t")
@JsonSubTypes({
    @JsonSubTypes.Type(value = F.class),
    @JsonSubTypes.Type(value = FC.class),
    @JsonSubTypes.Type(value = GJO.class)
})
public abstract class GJO {

  public abstract GeoJsonObject prettify();

}
