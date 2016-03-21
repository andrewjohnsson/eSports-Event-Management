package by.bsuir.spp.ils.lab.helper;

import by.bsuir.spp.ils.lab.entity.User;

import javax.json.*;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import javax.json.stream.JsonGeneratorFactory;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by andrewjohnsson on 09.03.16.
 */
public class JSONHelper {
  private StringWriter stringWriter;
  private StringReader stringReader;
  private final Map<String, String> generatorConfig = new HashMap<>();
  private JsonGenerator generator;
  private JsonGeneratorFactory genFactory;

  public JSONHelper(){
    this.configure();
  }

  private void configure(){
    stringWriter = new StringWriter();
    generatorConfig.put(JsonGenerator.PRETTY_PRINTING, "true");
    genFactory = Json.createGeneratorFactory(generatorConfig);
    generator = genFactory.createGenerator(stringWriter);
  }

  public User parseUser(String input){
    User user = new User();
    stringReader = new StringReader(input);
    JsonParser jsonParser = Json.createParser(this.stringReader);

    while (jsonParser.hasNext()) {
      Event event = jsonParser.next();
      switch (event) {
        case VALUE_STRING:
          switch (jsonParser.getString()){
            case "name":
              user.setName(jsonParser.getString());
              break;
            case "login":
              user.setLogin(jsonParser.getString());
              break;
            case "password":
              user.setPassword(jsonParser.getString());
              break;
            case "email":
              user.setEmail(jsonParser.getString());
              break;
          }
          break;
        case VALUE_NUMBER:
          switch (jsonParser.getString()){
            case "id":
              user.setUserId(jsonParser.getInt());
              break;
            case "age":
              user.setAge(jsonParser.getInt());
              break;
          }
          break;
      }
    }
    return user;
  }

  private void generateJSONObjectList(List<User> users){
    generator.writeStartObject();
      generator.writeStartArray("users");
        users.forEach(user -> {
          generateJSONObject(user);
        });
      generator.writeEnd();
    generator.writeEnd();
  }

  private void generateJSONObject(User user){
    generator.writeStartObject();
    if (0 < user.getUserId()){
      generator.write("id", user.getUserId());
    }
    if (null != user.getName()){
      generator.write("name", user.getName());
    }
    if (null != user.getLogin()){
      generator.write("login", user.getLogin());
    }
    if (null != user.getPassword()){
      generator.write("password", user.getPassword());
    }
    if (null != user.getEmail()){
      generator.write("email", user.getEmail());
    }
    if (0 < user.getAge()){
      generator.write("age", user.getAge());
    }
    generator.writeEnd();
  }

  public String getObject(User user){
    this.generateJSONObject(user);
    generator.close();
    return stringWriter.toString();
  }

  public String getList(List<User> users){
    this.generateJSONObjectList(users);
    generator.close();
    return stringWriter.toString();
  }

}
