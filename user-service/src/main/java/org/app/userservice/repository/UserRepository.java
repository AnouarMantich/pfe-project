package org.app.userservice.repository;

import org.app.userservice.model.User;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,String> {

    boolean existsByKeycloakId(String keycloakId);

}
