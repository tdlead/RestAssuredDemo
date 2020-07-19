import org.junit.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

public class DeleteMethodTests {
    @Test
    public void deleteTest(){
        when().delete("https://jsonplaceholder.typicode.com/posts/1").then().statusCode(200);
    }
}
