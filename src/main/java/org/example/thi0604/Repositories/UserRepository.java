package org.example.thi0604.Repositories;

import org.example.thi0604.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query("select s from User s where s.email=?1")
    User findByEmail(String email);
}
