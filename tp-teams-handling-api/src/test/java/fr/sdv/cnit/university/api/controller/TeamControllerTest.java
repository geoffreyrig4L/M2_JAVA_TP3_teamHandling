package fr.sdv.cnit.university.api.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import fr.sdv.cnit.university.api.entity.Team;
import fr.sdv.cnit.university.api.repository.TeamRepository;
import fr.sdv.cnit.university.api.service.TeamService;
import io.restassured.http.ContentType;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class TeamControllerTest {

    @Autowired
    TeamRepository teamRepository;

    @BeforeEach
    void setUpRestAssured() {
        TeamService teamService = new TeamService(teamRepository);
        TeamController teamController = new TeamController(teamService);
        standaloneSetup(teamController);
    }

    @BeforeEach
    void insertInH2() {
        insertTeamInH2(1L, "Benfica", "Pas de concu");
        insertTeamInH2(2L, "Brighton", "La victoire ou la mort !");
        insertTeamInH2(3L, "Arsenal", "Perdre, c'est quoi ?");
    }

    private void insertTeamInH2(Long id, String nom, String slogan) {
        Team team = new Team();
        team.setId(id);
        team.setName(nom);
        team.setSlogan(slogan);
        teamRepository.save(team);
    }

    @ParameterizedTest
    @MethodSource("provideTeamsAttribute")
    void shouldGetAllTeams(int id, String expectedName) {
        given()
                .when()
                .get("/teams")
                .then()
                .statusCode(200)
                .body("[" + id + "].name", equalTo(expectedName));
    }

    private static Stream<Arguments> provideTeamsAttribute() {
        return Stream.of(
                Arguments.of(0, "Benfica"),
                Arguments.of(1, "Brighton"),
                Arguments.of(2, "Arsenal"));
    }

    @Test
    void shouldGetTeam() {
        given()
                .when()
                .get("/teams/2")
                .then()
                .statusCode(200)
                .body("name", equalTo("Brighton"));
    }

    @Test
    void shouldNotGetTeam() {
        given()
                .when()
                .get("/teams/50")
                .then()
                .statusCode(404);
    }

    @Test
    void shouldPutTeam() {
        given()
                .body("{\"id\":2,\"name\":\"Manchester City\"}")
                .contentType(ContentType.JSON)
                .when()
                .put("/teams/2")
                .then()
                .statusCode(200);

        given()
                .when()
                .get("/teams/2")
                .then()
                .statusCode(200)
                .body("name", equalTo("Manchester City"));
    }

    @Test
    void shouldDeleteTeam() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .delete("/teams/3")
                .then()
                .statusCode(200);
    }
}