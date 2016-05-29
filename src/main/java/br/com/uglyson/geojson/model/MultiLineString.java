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
