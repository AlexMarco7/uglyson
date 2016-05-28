package br.com.uglyson.geojson.model;

import br.com.uglyson.geojson.model.ugly.G;

public class Point extends Geometry<Position> {

  public G uglify(){
    G ugly = new G();
    ugly.setC(getCoordinates().uglify());
    return ugly;
  }

}
