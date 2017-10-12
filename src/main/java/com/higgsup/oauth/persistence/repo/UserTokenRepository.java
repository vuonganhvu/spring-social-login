package com.higgsup.oauth.persistence.repo;

import com.higgsup.oauth.persistence.model.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserTokenRepository extends JpaRepository<UserToken, Long> {

    @Query("select u.userSytemId from UserToken u where u.providerId = :providerId and u.providerUserId = :providerUserId ")
    Long getUserSystemId(@Param("providerUserId") String providerUserId, @Param("providerId")  String providerId);

}