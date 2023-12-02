package fr.sdv.cnit.university.api.dto;

import java.util.List;
import java.util.stream.Collectors;

import fr.sdv.cnit.university.api.entity.Team;

public record TeamDto(Long id, String name, String slogan) {

    public static TeamDto fromEntity(Team team) {
        return new TeamDto(team.getId(), team.getName(), team.getSlogan());
    }

    public static List<TeamDto> toDtoList(List<Team> teams) {
        return teams.stream()
                .map(TeamDto::fromEntity)
                .collect(Collectors.toList());
    }

    public static Team toEntity(TeamDto teamDto) {
        Team team = new Team();
        team.setId(teamDto.id());
        team.setName(teamDto.name());
        team.setSlogan(teamDto.slogan());
        return team;
    }
}
