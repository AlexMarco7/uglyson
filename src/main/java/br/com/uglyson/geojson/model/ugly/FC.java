package br.com.uglyson.geojson.model.ugly;

import br.com.uglyson.geojson.model.FeatureCollection;

import java.util.ArrayList;
import java.util.List;

public class FC extends GJO {

  List<F> f = new ArrayList<F>();

  public List<F> getF() {
    return f;
  }

  public FC setF(List<F> f) {
    this.f = f;
    return this;
  }

  public FeatureCollection prettify(){
    FeatureCollection featureCollection = new FeatureCollection();

    for(F ff : f)
      featureCollection.getFeatures().add(ff.prettify());

    return featureCollection;
  }
}
