package fr.sdv.cnit.university.api.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
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

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class TeamServiceTest {

	@Autowired
	private TeamService teamService;

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
		teamService.save(team);
	}

	@Test
	void shouldGetAllTeams() {
		List<Team> teams = teamService.getAllTeams();

		assertThat(teams).hasSize(3);

		assertThat(teams.get(0))
				.extracting(Team::getName, Team::getSlogan)
				.containsExactly("Benfica", "Pas de concu");

		assertThat(teams.get(1))
				.extracting(Team::getName, Team::getSlogan)
				.containsExactly("Brighton", "La victoire ou la mort !");

		assertThat(teams.get(2))
				.extracting(Team::getName, Team::getSlogan)
				.containsExactly("Arsenal", "Perdre, c'est quoi ?");
	}

	@ParameterizedTest
	@MethodSource("provideTeamsAttribute")
	void shouldGetTeam(Long id, String expectedName) {
		Team team = teamService.get(id).orElse(null);
		assertThat(team)
				.isNotNull()
				.extracting(Team::getName)
				.isEqualTo(expectedName);
	}

	private static Stream<Arguments> provideTeamsAttribute() {
		return Stream.of(
				Arguments.of(1L, "Benfica"),
				Arguments.of(2L, "Brighton"),
				Arguments.of(3L, "Arsenal"));
	}

	@Test
	void shouldSaveNewTeam() {
		Team team = new Team();
		team.setId(4L);
		team.setName("Inter Milan");
		team.setSlogan("Aucune défaite dans notre palmarès");
		teamService.save(team);

		Team result = teamService.get(4L).orElse(null);
		assertThat(result)
				.isNotNull()
				.extracting(Team::getName, Team::getSlogan)
				.containsExactly("Inter Milan", "Aucune défaite dans notre palmarès");
	}

	@Test
	void shouldUpdateTeam() {
		Team teamToUpdate = teamService.get(1L).orElse(null);
		assertThat(teamToUpdate)
				.isNotNull();

		teamToUpdate.setName("FC Barcelone");
		teamToUpdate.setSlogan("Objectif : balle en feu");

		Team result = teamService.save(teamToUpdate);
		assertThat(result)
				.isNotNull()
				.extracting(Team::getId, Team::getName, Team::getSlogan)
				.containsExactly(1L, "FC Barcelone", "Objectif : balle en feu");
	}

	@Test
	void shouldDeleteTeam() {
		teamService.delete(4L);
		Team result = teamService.get(4L).orElse(null);
		assertThat(result)
				.isNull();
	}
}
