package fr.sdv.cnit.university.api.service;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import fr.sdv.cnit.university.api.H2TestJpaConfig;
import fr.sdv.cnit.university.api.entity.Team;
import fr.sdv.cnit.university.api.repository.TeamRepository;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class TeamServiceTest implements H2TestJpaConfig {

	@Autowired
	public MockMvc mockMvc;

	@Autowired
	public TeamRepository teamRepository;

	@InjectMocks
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
		teamRepository.save(team);
	}

	@Test
	void testGetAllTeams() {
		List<Team> teams = teamRepository.findAll();
		for (Team team : teams) {
			System.out.println(team.getName());
			System.out.println(team.getSlogan());
			System.out.println(team.getId());
		}
	}

	@Test
	void testGet() {

	}

	@Test
	void testSave() {

	}

	@Test
	void testUpdate() {

	}

	@Test
	void testDelete() {

	}
}
