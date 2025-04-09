package com.pszeniczny.atiperatask;

import com.pszeniczny.atiperatask.exceptions.ErrorResponse;
import com.pszeniczny.atiperatask.model.ResponseModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureWebClient
public class GhApiIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    public void setUp() {
        webTestClient = webTestClient.mutate()
                .responseTimeout(Duration.ofMillis(10000))
                .build();
    }

    @Test
    public void testGetUserRepositories_WithJsonAcceptHeader() {
        String username = "patrykpszeniczny";

        webTestClient.get()
                .uri("/api/v1/atiperatask/" + username)
                .header("Accept", "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(ResponseModel.class);
    }

    @Test
    public void testGetUserRepositories_WithXmlAcceptHeader_ShouldReturnError() {
        String username = "patrykpszeniczny";

        webTestClient.get()
                .uri("/api/v1/atiperatask/" + username)
                .header("Accept", "application/xml")
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_ACCEPTABLE)
                .expectBody(ErrorResponse.class);
    }

    @Test
    public void testGetUserRepositories_WithNoExistingUser_ShouldReturnError() {
        String username = "patrykpszeniczny1232112";

        webTestClient.get()
                .uri("/api/v1/atiperatask/" + username)
                .header("Accept", "application/json")
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_FOUND)
                .expectBody(ErrorResponse.class)
                .isEqualTo(new ErrorResponse(404, "No repositories found for user: " + username));
    }
}