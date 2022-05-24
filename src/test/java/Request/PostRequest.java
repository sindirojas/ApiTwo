package Request;

import Utils.SetProperties;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.json.JSONObject;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class PostRequest extends SetProperties {
    JSONObject jsonObject = new JSONObject();
    GetRequest getRequest = new GetRequest();
    private String validateTokenPath = "/authentication/token/validate_with_login";
    private String createSession = "/authentication/session/new";
    private String rateMoviePath = "/movie/";
    private String createListPath = "/list";

    public PostRequest(){super();}

    public String validateToken(){
        jsonObject
                .put("username", getUsername())
                .put("password", getPassword())
                .put("request_token", getRequest.generateToken());
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key", getApi_key())
                .body(jsonObject.toString())
                .when()
                .post(getBase_url() + validateTokenPath)
                .then()
                .statusCode(200)
                .extract().response();
        Assert.assertEquals("true", response.jsonPath().getString("success"));
        return response.jsonPath().getString("request_token");
    }

    public String createSession(){
        jsonObject
                .put("request_token", validateToken());
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key", getApi_key())
                .body(jsonObject.toString())
                .when()
                .post(getBase_url() + createSession)
                .then()
                .statusCode(200)
                .extract().response();
        Assert.assertEquals("true", response.jsonPath().getString("success"));
        return response.jsonPath().getString("session_id");
    }

    public void rateMovie (int idMovie, int rate) {
        jsonObject
                .put("value", rate);
        ValidatableResponse response =given()
                .contentType(ContentType.JSON)
                .queryParam("api_key", getApi_key())
                .queryParam("session_id", createSession())
                .body(jsonObject.toString())
                .when()
                .post(getBase_url() + rateMoviePath + idMovie + "/rating")
                .then()
                .log()
                .body();
    }

    public int createList (String name, String description){
        jsonObject
                .put("name", name)
                .put("description", name);
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key", getApi_key())
                .queryParam("session_id", createSession())
                .body(jsonObject.toString())
                .when()
                .post(getBase_url() + createListPath)
                .then()
                .statusCode(201)
                .extract().response();
        Assert.assertEquals("true", response.jsonPath().getString("success"));
        Assert.assertEquals("The item/record was created successfully.", response.jsonPath().getString("status_message"));
        return response.jsonPath().getInt("list_id");

    }
}
