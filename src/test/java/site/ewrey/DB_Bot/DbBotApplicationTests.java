package site.ewrey.DB_Bot;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;

//@SpringBootTest
class DbBotApplicationTests {

	@AfterEach
	void doAny(){
		System.out.println("AFTER");
	}

	@Test
	void succesSendMessage() {
		RestAssured.baseURI = "https://api.telegram.org/bot5782481564:AAEIqdv8PKRi3mR88EXbImRxn9QwJbPFaQk";
		given()
				.param("text", "rest-assured_TEST")
				.param("chat_id", "362396673")
				.when()
				.get("/sendMessage")
				.then()
				.statusCode(200);
	}

	@Test
	void unSuccessMessage(){
		RestAssured.baseURI = "https://api.telegram.org/bot5782481564:AAEIqdv8PKRi3mR88EXbImRxn9QwJbPFaQk";
		given()
				.param("text", "rest-assured_TEST")
				.param("chat_id", "362396673")
				.param("parse_mode", "Markdown")
				.when()
				.get("/sendMessage")
				.then()
				.statusCode(400);
	}
}
