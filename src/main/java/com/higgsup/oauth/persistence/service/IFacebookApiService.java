package com.higgsup.oauth.persistence.service;

import com.higgsup.oauth.persistence.api.FacebookAccessToken;
import com.higgsup.oauth.persistence.dto.AccessTokenDTO;
import com.higgsup.oauth.persistence.dto.UserFacebookDTO;

public interface IFacebookApiService {
    FacebookAccessToken exchange(AccessTokenDTO accessToken) ;
    UserFacebookDTO getProfile(String token);
}
