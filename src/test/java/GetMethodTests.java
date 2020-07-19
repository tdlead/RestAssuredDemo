import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetMethodTests {
    @Test
    public void validateSuccessfulResponseCode(){
       get("https://jsonplaceholder.typicode.com/posts/1").then().assertThat().statusCode(200);
    }
    @Test
    public void validateThatBodyContains(){
        get("https://jsonplaceholder.typicode.com/posts/1").then().body("body", containsString("reprehenderit molestiae ut ut quas totam"));
    }
    @Test
    public void validateThatTitleContains(){
        get("https://jsonplaceholder.typicode.com/posts/1").then().body("title", is("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));
    }
    @Test
    public void validateThatListContainsIds(){
        get("https://jsonplaceholder.typicode.com/posts/").then().body("id",hasItems(43));
    }
}
