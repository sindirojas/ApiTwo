package Request;

import Utils.SetProperties;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class DeleteRequest extends SetProperties {
    private String deleteMovieRate = "/movie/";
    private String deleteListPath = "/list/";
    private String deleteTvRate = "/tv/";
    PostRequest postRequest = new PostRequest();
    public DeleteRequest (){super();}

    public void deleteRating (int idMovie){
        Response response = given()
                .queryParam("api_key", getApi_key())
                .queryParam("session_id", postRequest.createSession())
                .when()
                .delete(getBase_url() + deleteMovieRate + idMovie + "/rating")
                .then()
                .extract().response();
    }

    public void deleteRatingTV (int idTv){
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key", getApi_key())
                .queryParam("session_id", postRequest.createSession())
                .when()
                .delete(getBase_url() + deleteTvRate + idTv + "/rating")
                .then()
                .statusCode(200)
                .extract().response();
        Assert.assertEquals("true", response.jsonPath().getString("success"));

    }

    public void deleteList(){
        int idlist = postRequest.createList("forDelete", "empty");
        System.out.println(idlist);
        System.out.println(getBase_url() + deleteListPath + idlist);
        ValidatableResponse response =given()
                .queryParam("api_key", getApi_key())
                .queryParam("session_id", postRequest.createSession())
                .when()
                .delete(getBase_url() + deleteListPath + idlist)
                .then()
                .log()
                .body();
    }

}
