package com.delivery_track.api.repositories;

import com.delivery_track.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "SELECT * FROM users WHERE name = :email", nativeQuery = true)
    Optional<User> findUserByEmail(@Param("email")String email);
}
