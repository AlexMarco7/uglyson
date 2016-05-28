package br.com.uglyson.geojson.model;

import br.com.uglyson.geojson.model.ugly.G;

import java.util.ArrayList;
import java.util.List;

public class LineString extends Geometry<List<Position>> {

  public G uglify(){
    G ugly = new G();
    ArrayList<Object> c = new ArrayList<Object>();

    c.add(1);

    for(Position p : getCoordinates())
      c.add(p.uglify());

    ugly.setC(c);

    return ugly;
  }

}
