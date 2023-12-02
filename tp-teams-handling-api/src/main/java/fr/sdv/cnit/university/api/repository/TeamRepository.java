package fr.sdv.cnit.university.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.sdv.cnit.university.api.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

}
