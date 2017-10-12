package com.higgsup.oauth.persistence.repo;

import com.higgsup.oauth.persistence.model.UserSystem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSystemRepository extends JpaRepository<UserSystem, Long> {
}
