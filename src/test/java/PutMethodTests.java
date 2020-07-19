import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PutMethodTests {
    @Test
    public void putBody(){
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("userId", 2);
        jsonAsMap.put("title", "PutMethod");
        jsonAsMap.put("body", "sexy body");

        given().
                contentType(ContentType.JSON).
                body(jsonAsMap).
                when().
                put("https://jsonplaceholder.typicode.com/posts/1").
                then().
                statusCode(200);
    }
}
