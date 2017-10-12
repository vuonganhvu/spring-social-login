package com.higgsup.oauth.persistence.dto;

public class AccessTokenDTO {
    private String token;
    private String providerId;
    private String prviderUserId;
    private Long userSystemId;

    public AccessTokenDTO() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getPrviderUserId() {
        return prviderUserId;
    }

    public void setPrviderUserId(String prviderUserId) {
        this.prviderUserId = prviderUserId;
    }

    public Long getUserSystemId() {
        return userSystemId;
    }

    public void setUserSystemId(Long userSystemId) {
        this.userSystemId = userSystemId;
    }
}
