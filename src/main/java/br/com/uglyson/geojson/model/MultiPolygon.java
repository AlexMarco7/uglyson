/*
MIT License

Copyright (c) 2016 AlexMarco7 https://github.com/AlexMarco7

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

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
