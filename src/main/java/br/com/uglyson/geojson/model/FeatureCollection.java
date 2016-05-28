package br.com.uglyson.geojson.model;

import br.com.uglyson.geojson.model.ugly.F;
import br.com.uglyson.geojson.model.ugly.FC;

import java.util.ArrayList;
import java.util.List;

public class FeatureCollection extends GeoJsonObject{

  List<Feature> features = new ArrayList<Feature>();

  public List<Feature> getFeatures() {
    return features;
  }

  public FeatureCollection setFeatures(List<Feature> features) {
    this.features = features;
    return this;
  }

  public FC uglify(){
    FC ugly = new FC();
    ugly.setF(new ArrayList<F>());

    for(Feature f : features)
      ugly.getF().add(f.uglify());

    return ugly;
  }


}
