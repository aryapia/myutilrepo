package demo;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import utils.JSONUtil;
import utils.RedTestCasesFromExcel;
import utils.RestAssuredOAuth2;

import static io.restassured.RestAssured.*;

import static	io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Tests_POST {
  static String [][] mydata;
	static JSONObject [] request;
	static Map<String,String> globalProperyMap= new HashMap<>(10);
	public static String clientId = "some_client_id";
	public static String redirectUri = "some_redirect_uri";
	public static String scope = "some_scope";
	public static String username = "some_email";
	public static String password = "some_password";
	public static String accessToken;




	@BeforeTest
	public void readALlforme() throws Exception{
		 mydata= RedTestCasesFromExcel.read();
		//accessToken= RestAssuredOAuth2.getAccessToken();


	}





	@Test
	public void step1() {
		JSONObject request = JSONUtil.read(mydata[1][3]);

		given().
		header("Authorization", "Bearer " + accessToken).
		header("Content-Type","application/json").
		contentType(ContentType.JSON).
		accept(ContentType.JSON).
				body(request.toJSONString()).
		when().
		post(mydata[1][2]).
		then().
		statusCode(201).log().all();

	}

	@Test(description="I am made to fail purposely as I am checking that my respose code should be failed only")
	public void step2() {

		JSONObject request = JSONUtil.read(mydata[2][3]);

		given().
				header("Content-Type","application/json").
				contentType(ContentType.JSON).
				accept(ContentType.JSON).
				body(request.toJSONString()).
				when().
				post(mydata[2][2]).
				then().
				statusCode(201).assertThat().
				body("id", equalTo("abc")).
				log().all();

	}

	@Test
	public void step3() {

		JSONObject request = JSONUtil.read(mydata[3][3]);

		given().
				header("Content-Type","application/json").
				contentType(ContentType.JSON).
				accept(ContentType.JSON).
				body(request.toJSONString()).
				when().
				post(mydata[3][2]).
				then().
				statusCode(201).log().all();

	}

	@Test
	public void step4() {

		JSONObject request = JSONUtil.read(mydata[4][3]);

		given().
				header("Content-Type","application/json").
				contentType(ContentType.JSON).
				accept(ContentType.JSON).
				body(request.toJSONString()).
				when().
				post(mydata[4][2]).
				then().
				statusCode(200).log().all();

	}



	@Test(description="This gives me session data that I need to pass to my next request")
	public void step5() {

		JSONObject request = JSONUtil.read(mydata[5][3]);

		given().
				header("Content-Type","application/json").
				contentType(ContentType.JSON).
				accept(ContentType.JSON).
				body(request.toJSONString()).
				when().
				post(mydata[5][2]).
				then().
				statusCode(200).log().all();

		ExtractableResponse<Response> extract = given().
				header("Content-Type", "application/json").
				contentType(ContentType.JSON).
				accept(ContentType.JSON).
				body(request.toJSONString()).
				when().
				post(mydata[5][2]).
				then().
				statusCode(200).extract();
				String Path1 = extract.path("token");
				String Path2 =  extract.path("id").toString();
				System.out.println(Path1);
				System.out.println(Path2);
				globalProperyMap.put("session",Path1);
				globalProperyMap.put("id",Path2);



	}


	@Test(description="I got session data from my previous request and dynamically modifying the orignal json")
	public void step6() {

		JSONObject request = JSONUtil.read(mydata[6][3]);
		DocumentContext documentContext = JsonPath.parse(request.toJSONString());
		documentContext.set("$.password",globalProperyMap.get("session"));
		String requestj = documentContext.jsonString();
		System.out.println(requestj);

		given().
				header("Content-Type","application/json").
				contentType(ContentType.JSON).
				accept(ContentType.JSON).
				body(requestj).
				when().
				post(mydata[6][2]).
				then().
				statusCode(200).log().all();

	}


	@Test
	public void step7() {

		JSONObject request = JSONUtil.read(mydata[7][3]);

		given().
				header("Content-Type","application/json").
				contentType(ContentType.JSON).
				accept(ContentType.JSON).
				body(request.toJSONString()).
				when().
				post(mydata[7][2]).
				then().
				statusCode(200).log().all();

	}

	@Test
	public void step8() {

		JSONObject request = JSONUtil.read(mydata[8][3]);

		given().
				header("Content-Type","application/json").
				contentType(ContentType.JSON).
				accept(ContentType.JSON).
				body(request.toJSONString()).
				when().
				post(mydata[8][2]).
				then().
				statusCode(200).log().all();

	}

	@Test
	public void step9() {

		JSONObject request = JSONUtil.read(mydata[7][3]);

		given().
				header("Content-Type","application/json").
				contentType(ContentType.JSON).
				accept(ContentType.JSON).
				body(request.toJSONString()).
				when().
				post(mydata[9][2]).
				then().
				statusCode(200).log().all();

	}

	@Test
	public void step10() {

		JSONObject request = JSONUtil.read(mydata[10][3]);

		given().
				header("Content-Type","application/json").
				contentType(ContentType.JSON).
				accept(ContentType.JSON).
				body(request.toJSONString()).
				when().
				post(mydata[10][2]).
				then().
				statusCode(400).assertThat().
				body("error", equalTo("Missing password")).
				log().all();
		//assertThat().
				//body("MRData.CircuitTable.Circuits.circuitId",hasSize(numberOfRaces));
	}



	@Test
	public void RNDStep() {
		RestAssured.baseURI ="https://reqbin.com/echo/post/json";
		RequestSpecification request = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("FirstName", "Virender"); // Cast
		requestParams.put("LastName", "Singh");
		requestParams.put("UserName", "sdimpleuser2dd2011");
		requestParams.put("Password", "password1");

		requestParams.put("Email",  "sample2ee26d9@gmail.com");
		request.body(requestParams.toJSONString());
		Response response = request.post("/register");

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, "200");
		String successCode = response.jsonPath().get("SuccessCode");
		Assert.assertEquals( "Correct Success code was returned", successCode, "OPERATION_SUCCESS");

	}

}
