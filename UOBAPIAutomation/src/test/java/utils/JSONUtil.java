package utils;


import java.io.FileNotFoundException;
       import java.io.FileReader;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
       

//import static io.restassured.path.json.JsonPath.*;


import io.restassured.path.json.config.JsonPathConfig;
import org.json.simple.JSONArray;
        import org.json.simple.JSONObject;
        import org.json.simple.parser.JSONParser;

public class JSONUtil
{





    public static JSONObject read(String request)
    {
        JSONParser parser = new JSONParser();
        try {
            Object object = parser
                    .parse(new FileReader("data\\" + request ));
            System.out.println(request);

            //convert Object to JSONObject
         JSONObject jsonObject = (JSONObject) object;
         /*
         //   String jsonPathString= from(jsonObject.toJSONString()).getString("subjects[1]");

          //  System.out.println(jsonObject.toJSONString());
            //String hello = jsonObject.get("subjects[1].id").toString();
           // System.out.println(jsonPathString);

            DocumentContext documentContext = JsonPath.parse(jsonObject.toJSONString());
            System.out.println(documentContext.jsonString());

           // String read = documentContext.read(jsonPathExpressionForDistrict);

           // System.out.println(read);
            documentContext.set("$['subjects'][0]['id']", "District2");

            System.out.println(documentContext.jsonString());
            //documentContext.read()
*/






         /*   //Reading the String
            String name = (String) jsonObject.get("Name");
            Long age = (Long) jsonObject.get("Age");

            //Reading the array
            JSONArray countries = (JSONArray)jsonObject.get("Countries");

            //Printing all the values
            System.out.println("Name: " + name);
            System.out.println("Age: " + age);
            System.out.println("Countries:");
            for(Object country : countries)
            {
                System.out.println("\t"+country.toString());
            }
        }*/
            return jsonObject;
        }
        catch(FileNotFoundException fe)
        {
            fe.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

}