package br.com.uglyson.geojson.model;

import br.com.uglyson.geojson.model.ugly.F;

import java.util.Map;

public class Feature extends GeoJsonObject{
  public Geometry geometry;
  public Map<String,Object> properties;

  public Geometry getGeometry() {
    return geometry;
  }

  public Feature setGeometry(Geometry geometry) {
    this.geometry = geometry;
    return this;
  }


  public Map<String, Object> getProperties() {
    return properties;
  }

  public Feature setProperties(Map<String, Object> properties) {
    this.properties = properties;
    return this;
  }

  public F uglify(){
    F ugly = new F();
    ugly.setP(getProperties());
    ugly.setG(getGeometry().uglify());

    return ugly;
  }

}
