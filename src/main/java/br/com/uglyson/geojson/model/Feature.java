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

import br.com.uglyson.geojson.model.ugly.F;

import java.util.Map;

public class Feature extends GeoJsonObject{
  public Geometry geometry;
  public Map<String,Object> properties;

  public Geometry getGeometry() {
    return geometry;
  }

  public Feature setGeometry(Geometry geometry) {
    this.geometry = geometry;
    return this;
  }


  public Map<String, Object> getProperties() {
    return properties;
  }

  public Feature setProperties(Map<String, Object> properties) {
    this.properties = properties;
    return this;
  }

  public F uglify(){
    F ugly = new F();
    ugly.setP(getProperties());
    ugly.setG(getGeometry().uglify());

    return ugly;
  }

}
