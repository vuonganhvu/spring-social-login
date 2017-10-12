package com.higgsup.oauth.persistence.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserToken {

    @Id
    private Long userSytemId;
    private String providerId;
    private String providerUserId;
    private String accessToken;
    private Long expires;
    private String tokenType;

    public UserToken() {
    }

    public Long getUserSytemId() {
        return userSytemId;
    }

    public void setUserSytemId(Long userSytemId) {
        this.userSytemId = userSytemId;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getProviderUserId() {
        return providerUserId;
    }

    public void setProviderUserId(String providerUserId) {
        this.providerUserId = providerUserId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpires() {
        return expires;
    }

    public void setExpires(Long expires) {
        this.expires = expires;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
