package br.com.uglyson.geojson.model;

import br.com.uglyson.geojson.model.ugly.G;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 26/05/16.
 */
public class Polygon extends Geometry<List<List<Position>>> {

  public G uglify(){
    G ugly = new G();

    ArrayList<Object> l = new ArrayList<Object>();

    l.add(3);

    for(List<Position> c2 : getCoordinates()) {
      ArrayList<Object> c = new ArrayList<Object>();
      for (Position p : c2) {
        c.add(p.uglify());
      }
      if(c.get(0).equals(c.get(c.size()-1)))
        c.remove(c.size()-1);
      l.add(c);
    }

    ugly.setC(l);

    return ugly;
  }

}
