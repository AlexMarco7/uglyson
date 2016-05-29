package br.com.uglyson.geojson.model;
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


import br.com.uglyson.geojson.model.serializer.PositionDeserializer;
import br.com.uglyson.geojson.model.serializer.PositionSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonDeserialize(using = PositionDeserializer.class)
@JsonSerialize(using = PositionSerializer.class)
public class Position {
  private Double lat;
  private Double lng;

  public Double getLat() {
    return lat;
  }

  public Position setLat(Double lat) {
    this.lat = lat;
    return this;
  }

  public Double getLng() {
    return lng;
  }

  public Position setLng(Double lng) {
    this.lng = lng;
    return this;
  }


  public long uglify(){
    return Math.round((180+getLng()) * 100000) * 100000000 + Math.round((90+getLat()) * 100000);
  }

  public static Position prettify(Object o){
    Position position = new Position();

    long l = o instanceof Integer ? ((Integer)o).longValue() : (Long)o;

    double lng = l / 100000000L / 100000.0 - 180;
    double lat = l % 100000000L / 100000.0 - 90;

    return position.setLng(lng).setLat(lat);
  }

}
