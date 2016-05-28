package br.com.uglyson.geojson.model.ugly;

import br.com.uglyson.geojson.model.Feature;

import java.util.Map;

public class F extends GJO {
  public G g;
  public Map<String, Object> p;

  public G getG() {
    return g;
  }

  public F setG(G g) {
    this.g = g;
    return this;
  }

  public Map<String, Object> getP() {
    return p;
  }

  public F setP(Map<String, Object> p) {
    this.p = p;
    return this;
  }

  public Feature prettify(){
    Feature feature = new Feature();

    feature.setProperties(getP());
    feature.setGeometry(getG().prettify());

    return feature;
  }
}
