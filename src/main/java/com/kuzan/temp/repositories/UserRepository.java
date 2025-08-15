package com.kuzan.temp.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kuzan.temp.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String>{

    // @Query("SELECT u FROM UserModel u WHERE u.email = :email")
    // Optional<UserModel> findByEmail(@Param("email") String email);
    Optional<UserModel> findByEmail(@Param("email") String email);
}
