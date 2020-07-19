
import io.restassured.http.ContentType;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;


public class PostMethodTests {
    @Test
    public void postBody(){
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("userId", 1);
        jsonAsMap.put("title", "TaniushaMaladet");
        jsonAsMap.put("body", "sexy body");

        given().
                contentType(ContentType.JSON).
                body(jsonAsMap).
                when().
                post("https://jsonplaceholder.typicode.com/posts").
                then().
                statusCode(201);
    }
}
