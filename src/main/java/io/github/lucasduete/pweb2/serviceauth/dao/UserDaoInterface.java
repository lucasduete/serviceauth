package io.github.lucasduete.pweb2.serviceauth.dao;

import io.github.lucasduete.pweb2.serviceauth.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDaoInterface extends JpaRepository<User, String> {

    @Query("SELECT u FROM User u WHERE matricula = ?1 AND password = ?2")
    User authenticate(String matricula, String password);
}
