package br.com.uglyson.geojson.model;

import br.com.uglyson.geojson.model.ugly.G;

import java.util.ArrayList;
import java.util.List;

public class MultiPolygon extends Geometry<List<List<List<Position>>>> {

  public G uglify(){
    G ugly = new G();

    ArrayList<Object> ps = new ArrayList<Object>();

    ps.add(4);
    for(List<List<Position>> c1 : getCoordinates()) {
      ArrayList<Object> l = new ArrayList<Object>();
      for (List<Position> c2 : c1) {
        ArrayList<Object> c = new ArrayList<Object>();
        for (Position p : c2) {
          c.add(p.uglify());
        }
        if(c.get(0).equals(c.get(c.size()-1)))
          c.remove(c.size()-1);
        l.add(c);
      }
      ps.add(l);
    }

    ugly.setC(ps);

    return ugly;
  }


}
