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
        PostDetails postDetails=new PostDetails("1","Stefan Krasavcik","sexy body");
        given().
                contentType(ContentType.JSON).
                body(postDetails).
                when().
                post(baseUrl).
                then().
                statusCode(201);
    }

    @Test
    public void putBody() {
        PostDetails postDetails=new PostDetails("1","PutMethod","sexy body");

        given().
                contentType(ContentType.JSON).
                body(postDetails).
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
        List<PostDetails> postDetailsList = given()
                .when()
                .get(baseUrl)
                .then()
                .extract()
                .body()
                .jsonPath().getList(".", PostDetails.class);
        Assert.assertEquals(100, postDetailsList.size());
        postDetailsList.forEach(this::checkUserIdAndTitleForPost);
    }

    private void checkUserIdAndTitleForPost(PostDetails postDetails){
        Assert.assertTrue(postDetails.getId() !=null || postDetails.getTitle()!=null);
    }
}
