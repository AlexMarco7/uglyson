package br.com.uglyson.geojson.model.ugly;

import br.com.uglyson.geojson.model.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class G extends GJO {

  private Object c;

  public Object getC() {
    return c;
  }

  public G setC(Object c) {
    this.c = c;
    return this;
  }
  public Geometry prettify(){

    Object o = getC();

    Geometry g = null;

    if(o instanceof Long){
      long l = (Long) o;

      g = new Point().setCoordinates(Position.prettify(l));
    }else if(o instanceof ArrayList) {
      ArrayList array = ((ArrayList) o);
      Iterator it = array.iterator();

      Integer type = (Integer) it.next();

      switch (type) {
        case 0: {
          ArrayList<Position> points = new ArrayList<Position>();
          while(it.hasNext()){
            points.add(Position.prettify(it.next()));
          }
          g = new MultiPoint().setCoordinates(points);
        }break;
        case 1: {
          ArrayList<Position> points = new ArrayList<Position>();
          while(it.hasNext()){
            points.add(Position.prettify(it.next()));
          }
          g = new LineString().setCoordinates(points);
        }
        break;
        case 2: {
          ArrayList<List<Position>> lines = new ArrayList<List<Position>>();
          while(it.hasNext()){
            ArrayList<Position> points = new ArrayList<Position>();
            for(Object x : (ArrayList)it.next()) {
              points.add(Position.prettify( x));
            }
            lines.add(points);
          }
          g = new MultiLineString().setCoordinates(lines);
        }
        break;
        case 3: {
          ArrayList<List<Position>> lines = new ArrayList<List<Position>>();
          while(it.hasNext()){
            ArrayList<Position> points = new ArrayList<Position>();
            for(Object x : (ArrayList)it.next()) {
              points.add(Position.prettify( x));
            }
            points.add(points.get(0));
            lines.add(points);
          }
          g = new Polygon().setCoordinates(lines);
        }
        break;
        case 4: {
          ArrayList<List<List<Position>>> polygons = new ArrayList<List<List<Position>>>();
          while(it.hasNext()){
            ArrayList<List<Position>> lines = new ArrayList<List<Position>>();
            for(Object y : (ArrayList)it.next()) {
              ArrayList<Position> points = new ArrayList<Position>();
              for(Object x : (ArrayList)y) {
                points.add(Position.prettify(x));
              }
              points.add(points.get(0));
              lines.add(points);
            }
            polygons.add(lines);
          }
          g = new MultiPolygon().setCoordinates(polygons);
        }
        break;
      }
    }

    return g;
  }



}
