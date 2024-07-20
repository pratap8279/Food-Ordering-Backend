package com.rudra.repositatory;

import com.rudra.model.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositatory extends JpaRepository<User, Integer> {
    public User findByEmail(String username);

}
