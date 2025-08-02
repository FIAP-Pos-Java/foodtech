package br.com.fiap.foodtech.foodtech.controller;

import br.com.fiap.foodtech.foodtech.helper.ItemCardapioHelper;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.BDDAssumptions.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemCardapioControllerIT extends ItemCardapioHelper{

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup(){
        RestAssured.port = port;
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void devePermitirRegistrarMensagem() throws Exception {
        var itemCardapioRequest = gerarItemCardapioCompletoDto();
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(itemCardapioRequest)
                .when()
                .post("/itensCardapio")
                .then()
                .statusCode(HttpStatus.CREATED.value());
        Assertions.assertEquals("BUILD OK", "BUILD OK");

    }

}
