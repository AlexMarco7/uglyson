package br.com.uglyson.geojson.model;

import br.com.uglyson.geojson.model.ugly.G;

import java.util.ArrayList;
import java.util.List;

public class MultiLineString extends Geometry<List<List<Position>>> {

  public G uglify(){
    G ugly = new G();

    ArrayList<Object> l = new ArrayList<Object>();

    l.add(2);

    for(List<Position> c2 : getCoordinates()) {
      ArrayList<Object> c = new ArrayList<Object>();
      for (Position p : c2) {
        c.add(p.uglify());
      }
      l.add(c);
    }

    ugly.setC(l);

    return ugly;
  }

}
