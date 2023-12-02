// package fr.sdv.cnit.university.api.controller;

// import static org.hamcrest.Matchers.*;
// import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertThat;
// import static org.mockito.Mockito.*;
// import org.hamcrest.CoreMatchers;
// import org.junit.After;
// import org.junit.Before;
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.mockito.ArgumentCaptor;
// import org.mockito.Mock;
// import org.mockito.junit.MockitoJUnitRunner;

// import java.util.List;
// import java.util.Optional;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import fr.sdv.cnit.university.api.entity.Team;
// import fr.sdv.cnit.university.api.service.TeamService;

// @RunWith(MockitoJUnitRunner.class)
// public class TeamControllerTest {
// @Mock
// private TeamService teamService;

// private TeamController teamController;

// @Before
// public void setup() {
// this.teamController = new TeamController(teamService);
// }

// @Test
// public void shouldGetAllTeams() {
// List<Team> actualValue = teamController.getAllTeams();

// // TODO: assert scenario
// }

// @Test
// public void shouldGet() {
// // TODO: initialize args
// Long id;

// ResponseEntity<Team> actualValue = teamController.get(id);

// // TODO: assert scenario
// }

// @Test
// public void shouldCreate() {
// // TODO: initialize args
// Team teamToCreate;

// ResponseEntity<Team> actualValue = teamController.create(teamToCreate);

// // TODO: assert scenario
// }

// @Test
// public void shouldCreate() {
// // TODO: initialize args
// Long id;
// Team teamModified;

// ResponseEntity<Team> actualValue = teamController.create(id, teamModified);

// // TODO: assert scenario
// }

// @Test
// public void shouldDelete() {
// // TODO: initialize args
// Long id;

// ResponseEntity<String> actualValue = teamController.delete(id);

// // TODO: assert scenario
// }
// }
