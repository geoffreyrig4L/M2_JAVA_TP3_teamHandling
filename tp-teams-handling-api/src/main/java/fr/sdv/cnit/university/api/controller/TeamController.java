package fr.sdv.cnit.university.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.sdv.cnit.university.api.entity.Team;
import fr.sdv.cnit.university.api.service.TeamService;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> get(@PathVariable("id") final Long id) {
        Optional<Team> team = teamService.get(id);
        if (team.isPresent()) {
            return ResponseEntity.ok(team.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Team team) {
        boolean isCreated = teamService.save(team);
        if (isCreated) {
            return ResponseEntity.ok().body("L'équipe a été créée.");
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> create(@PathVariable("id") final Long id, @RequestBody Team teamModified) {
        Team team = teamService.update(id, teamModified);
        if (team != null) {
            return ResponseEntity.ok(team);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") final Long id) {
        teamService.delete(id);
        return ResponseEntity.ok().body("L'équipe a été supprimée (si elle existe).");
    }
}
