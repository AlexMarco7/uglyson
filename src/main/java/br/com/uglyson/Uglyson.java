package br.com.uglyson;

import br.com.uglyson.geojson.model.GeoJsonObject;
import br.com.uglyson.geojson.model.ugly.GJO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.*;

public class Uglyson {

  private static ObjectMapper mapper = new ObjectMapper();

  public static JsonNode uglifyGeoJson(JsonNode fc){
    try {
      GeoJsonObject geoJsonObject = mapper.treeToValue(fc, GeoJsonObject.class);
      return uglify(mapper.valueToTree(geoJsonObject.uglify()));

    }catch (Exception e){
      throw new RuntimeException(e);
    }
  }

  public static JsonNode prettifyGeoJson(JsonNode fc){
    try {
      GJO gjo = mapper.treeToValue(prettify(fc), GJO.class);
      return mapper.valueToTree(gjo.prettify());

    }catch (Exception e){
      throw new RuntimeException(e);
    }
  }

  public static String uglifyGeoJson(String fc){
    try {
      GeoJsonObject geoJsonObject = mapper.readValue(fc, GeoJsonObject.class);
      return uglify(mapper.valueToTree(geoJsonObject.uglify())).toString();

    }catch (Exception e){
      throw new RuntimeException(e);
    }
  }

  public static String prettifyGeoJson(String fc){
    try {
      GJO gjo = mapper.readValue(prettify(fc), GJO.class);
      return mapper.writeValueAsString(gjo.prettify());

    }catch (Exception e){
      throw new RuntimeException(e);
    }
  }


  public static String uglify(String json){
    try {
      return mapper.writeValueAsString(uglify(mapper.readTree(json)));
    }catch (Exception e){
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }
  public static JsonNode uglify(JsonNode json){
    HashMap<String, Integer> count = new HashMap<String, Integer>();
    LinkedList<Object[]> fields = new LinkedList<Object[]>();
    replaceNames(json, count, fields);

    Comparator<Object[]> comparator = new Comparator<Object[]>() {
      public int compare(Object[] o1, Object[] o2) {
        return ((Integer) o1[0]).compareTo((Integer) o2[0]);
      }
    };
    
    HashMap<String, Integer> map = new HashMap<String, Integer>();
    Set<Object[]> replacedSet = new TreeSet<Object[]>(comparator);

    for (Object[] f : fields){
      Integer id = map.get((String) f[1]);
      if(id == null)
        id = replacedSet.size();
      ((ObjectNode)f[0]).set("@" + id, (JsonNode) f[2]);
      ((ObjectNode)f[0]).remove((String) f[1]);
      replacedSet.add(new Object[]{id, f[1]});
      map.put((String)f[1],id);
    }

    ArrayList<Object[]> replaced = new ArrayList<Object[]>(replacedSet);

    Collections.sort(replaced, comparator);

    ArrayNode result = mapper.createArrayNode();
    result.add(json);
    for(Object[] o : replaced){
      result.add((String)o[1]);
    }

    return result;
  }

  private static void replaceNames(JsonNode json, Map<String, Integer> count, LinkedList<Object[]> fields){

    if(json.isArray()){
      for(JsonNode i : json){
        if(i.isArray() || i.isObject()){
          replaceNames(i, count, fields);
        }
      }
    }else if(json.isObject()){
      ObjectNode o = (ObjectNode) json;

      Iterator<String> it =  json.fieldNames();

      while(it.hasNext()){
        String f = it.next();
        JsonNode v = o.get(f);

        if(f.length() > 3){
          fields.add(new Object[]{o, f, v});
          Integer c = count.get(f);
          count.put(f, (c == null ? 0 : c) + 1);
        }

        if(v.isArray() || v.isObject()){
          replaceNames(v, count, fields);
        }
      }

    }
  }

  public static String prettify(String json){
    try {
      return mapper.writeValueAsString(prettify(mapper.readTree(json)));
    }catch (Exception e){
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  public static JsonNode prettify(JsonNode json){
    if(json.isArray()){
      ArrayNode arr = (ArrayNode) json;
      JsonNode result = arr.get(0);
      ArrayList<String> fields = new ArrayList<String>();

      for(JsonNode o : arr){
        if(!o.equals(result)){
          fields.add(o.asText());
        }
      }

      replaceIds(result, fields);

      return result;
    }else{
      return json;
    }
  }

  private static void replaceIds(JsonNode json, ArrayList<String> fields){

    if(json.isArray()){
      for(JsonNode i : json){
        if(i.isArray() || i.isObject()){
          replaceIds(i, fields);
        }
      }
    }else if(json.isObject()){
      ObjectNode o = (ObjectNode) json;

      Iterator<String> it =  json.fieldNames();

      LinkedList<Object[]> objs = new LinkedList<Object[]>();

      while(it.hasNext()){
        String f = it.next();
        JsonNode v = o.get(f);

        if(f.matches("@\\d+")){
          int i = Integer.valueOf(f.substring(1));
          //System.out.println(f +" ---- "+i);
          objs.add(new Object[]{f,fields.get(i)});
        }

        if(v.isArray() || v.isObject()){
          replaceIds(v, fields);
        }
      }

      for(Object[] oo : objs) {
        o.set((String) oo[1], o.get((String) oo[0]));
        o.remove((String)oo[0]);
      }

    }
  }

}
