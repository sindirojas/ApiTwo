package Request;

import Utils.SetProperties;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class GetRequest extends SetProperties {
    private String token_path = "/authentication/token/new";
    private String getListPath = "/list/";
    private String guestSessionidPath = "/authentication/guest_session/new";
    public GetRequest(){super();}

    public String generateToken(){
        Response response = given()
                .queryParam("api_key", getApi_key())
                .when()
                .get(getBase_url() + token_path)
                .then()
                .statusCode(200)
                .extract().response();
        Assert.assertEquals("true", response.jsonPath().getString("success"));
        return response.jsonPath().getString("request_token");
    }

    public void getList(){
        Response response = given()
                .queryParam("api_key", getApi_key())
                .when()
                .get(getBase_url() + getListPath + 8203898)
                .then()
                .statusCode(200)
                .extract().response();
        Assert.assertEquals("SindiRojas", response.jsonPath().getString("created_by"));
    }

    public String guestSessionid(){
        Response response = given()
                .queryParam("api_key", getApi_key())
                .when()
                .get(getBase_url() + guestSessionidPath)
                .then()
                .statusCode(200)
                .extract().response();
        Assert.assertEquals("true",response.jsonPath().getString("success"));
        return response.jsonPath().getString("guest_session_id");

    }


}
