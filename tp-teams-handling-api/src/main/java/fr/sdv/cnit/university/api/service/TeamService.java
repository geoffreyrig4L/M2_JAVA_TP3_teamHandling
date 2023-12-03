package fr.sdv.cnit.university.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.sdv.cnit.university.api.entity.Team;
import fr.sdv.cnit.university.api.repository.TeamRepository;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Optional<Team> get(final Long id) {
        return teamRepository.findById(id);
    }

    public Team save(Team team) {
        if (team.getSlogan() != null) {
            return teamRepository.save(team);
        }
        throw new TeamInvalidException("Le slogan de l'équipe ne peut pas être null");
    }

    public Team update(final Long id, Team modified) {
        Optional<Team> teamOptional = this.get(id);
        if (teamOptional.isPresent()) {
            Team team = teamOptional.get();
            if (team.getName() != null) {
                team.setName(modified.getName());
            }
            if (team.getSlogan() != null) {
                team.setSlogan(modified.getSlogan());
            }
            return teamRepository.save(team);
        }
        return null;
    }

    public void delete(Long id) {
        teamRepository.deleteById(id);
    }
}
