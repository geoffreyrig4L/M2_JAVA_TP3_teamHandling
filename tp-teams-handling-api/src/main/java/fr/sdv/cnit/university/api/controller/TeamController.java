package fr.sdv.cnit.university.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import fr.sdv.cnit.university.api.dto.TeamDto;
import fr.sdv.cnit.university.api.entity.Team;
import fr.sdv.cnit.university.api.service.TeamInvalidException;
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
    public List<TeamDto> getAllTeams() {
        List<Team> teams = teamService.getAllTeams();
        return TeamDto.toDtoList(teams);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDto> get(@PathVariable("id") final Long id) {
        Optional<Team> teamOptional = teamService.get(id);
        if (teamOptional.isPresent()) {
            return ResponseEntity.ok(TeamDto.fromEntity(teamOptional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<TeamDto> create(@RequestBody TeamDto teamDtoToCreate) {
        Team teamToCreate = TeamDto.toEntity(teamDtoToCreate);
        Team createdTeam = teamService.save(teamToCreate);
        return ResponseEntity.ok(TeamDto.fromEntity(createdTeam));
    }

    @ExceptionHandler(TeamInvalidException.class)
    public ResponseEntity<String> handleException(TeamInvalidException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + exception.getMessage());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamDto> update(@PathVariable("id") final Long id, @RequestBody TeamDto teamDtoModified) {
        Team teamModified = TeamDto.toEntity(teamDtoModified);
        Team updatedTeam = teamService.update(id, teamModified);
        return ResponseEntity.ok(TeamDto.fromEntity(updatedTeam));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") final Long id) {
        teamService.delete(id);
        return ResponseEntity.ok().body("L'équipe a été supprimée (si elle existe).");
    }
}
