import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RestAssuredTests {
    private static final String baseUrl = "https://jsonplaceholder.typicode.com/posts/";

    @Test
    public void validateSuccessfulResponseCode() {
        get(baseUrl + "1").
                then().statusCode(200).
                and().
                body("body", containsString("reprehenderit molestiae ut ut quas totam")).
                and().
                body("title", is("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));
    }

    @Test
    public void validateThatListContainsIds() {
        get(baseUrl).then().body("id", hasItems(43));
    }

    @Test
    public void postBody() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("userId", 1);
        jsonAsMap.put("title", "TaniushaMaladet");
        jsonAsMap.put("body", "sexy body");

        given().
                contentType(ContentType.JSON).
                body(jsonAsMap).
                when().
                post(baseUrl).
                then().
                statusCode(201);
    }

    @Test
    public void putBody() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("userId", 2);
        jsonAsMap.put("title", "PutMethod");
        jsonAsMap.put("body", "sexy body");

        given().
                contentType(ContentType.JSON).
                body(jsonAsMap).
                when().
                put(baseUrl + "1").
                then().
                statusCode(200);
    }

    @Test
    public void deleteTest() {
        when().delete(baseUrl + "1").then().statusCode(200);
    }

    @Test
    public void validateList() {
        List<User> userList = given()
                .when()
                .get(baseUrl)
                .then()
                .extract()
                .body()
                .jsonPath().getList(".", User.class);
        Assert.assertEquals(100, userList.size());
        Assert.assertTrue(userList.stream().anyMatch(x -> x.getId() != null && x.getTitle() != null));
    }
}
