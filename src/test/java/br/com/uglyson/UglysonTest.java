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

package br.com.uglyson;

import static org.junit.Assert.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class UglysonTest {

  @Test
  public void testFeatureCollection(){
    String json = "{\"type\":\"FeatureCollection\",\"features\":[{\"type\":\"Feature\",\"geometry\":{\"type\":\"Point\",\"coordinates\":[102.0,0.5]},\"properties\":{\"prop0\":\"value0\"}},{\"type\":\"Feature\",\"geometry\":{\"type\":\"LineString\",\"coordinates\":[[102.0,0.0],[103.0,1.0],[104.0,0.0],[105.0,1.0]]},\"properties\":{\"prop0\":\"value0\",\"prop1\":0.0}},{\"type\":\"Feature\",\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[100.0,0.0],[101.0,0.0],[101.0,1.0],[100.0,1.0],[100.0,0.0]]]},\"properties\":{\"prop0\":\"value0\",\"prop1\":{\"this\":\"that\"}}}]}";
    System.out.println(json.length());
    String ugly = Uglyson.uglifyGeoJson(json);
    System.out.println(ugly.length());
    String result = Uglyson.prettifyGeoJson(ugly);
    System.out.println(result.length());
    assertEquals(json, result);
    result = Uglyson.prettifyGeoJson(Uglyson.uglifyGeoJson(json));
    assertEquals(json, result);
  }

  @Test
  public void testUglify(){
    String json = null;
    try {
      json = new ObjectMapper().readTree(new File("../uglyson_test/test.json")).toString();
    } catch (IOException e) {
      e.printStackTrace();
    }
    long l = System.currentTimeMillis();
    System.out.println(json.length());
    String result = Uglyson.uglify(json);
    System.out.println(result.length() + " - " + (System.currentTimeMillis()-l));
    l = System.currentTimeMillis();
    result = Uglyson.prettify(result);
    System.out.println(result.length() + " - " + (System.currentTimeMillis() - l));
  }

  @Test
  public void testUglify2(){
    JsonNode json = null;
    try {
      json = new ObjectMapper().readTree(new File("../uglyson_test/test.json"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    long l = System.currentTimeMillis();
    JsonNode result = Uglyson.uglify(json);
    System.out.println(System.currentTimeMillis()-l);
    l = System.currentTimeMillis();
    result = Uglyson.prettify(result);
    System.out.println(System.currentTimeMillis()-l);
  }

}
