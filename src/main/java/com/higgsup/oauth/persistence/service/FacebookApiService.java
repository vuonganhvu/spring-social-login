package com.higgsup.oauth.persistence.service;

import com.higgsup.oauth.persistence.api.FacebookAPIConstant;
import com.higgsup.oauth.persistence.api.FacebookAccessToken;
import com.higgsup.oauth.persistence.dto.AccessTokenDTO;
import com.higgsup.oauth.persistence.dto.UserFacebookDTO;
import com.higgsup.oauth.persistence.model.UserToken;
import com.higgsup.oauth.persistence.repo.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class FacebookApiService implements IFacebookApiService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserTokenRepository userTokenRepository;

    @Value("${spring.social.facebook.appId}")
    private String appId;

    @Value("${spring.social.facebook.appSecret}")
    private String appSecret;

    @Override
    public FacebookAccessToken exchange(AccessTokenDTO shortLivedToken) {
        FacebookAccessToken longLivedToken = exchangeForLongLivedToken(shortLivedToken);
        UserToken userToken = new UserToken();
        userToken.setUserSytemId(shortLivedToken.getUserSystemId());
        userToken.setProviderId(shortLivedToken.getProviderId());
        userToken.setProviderUserId(shortLivedToken.getPrviderUserId());
        userToken.setAccessToken(longLivedToken.getAccessToken());
        userToken.setExpires(Long.parseLong(String.valueOf(longLivedToken.getExpiresIn())));
        userToken.setTokenType(longLivedToken.getTokenType());
        userTokenRepository.save(userToken);
        return longLivedToken;
    }

    @Override
    public UserFacebookDTO getProfile(String token) {
        String listFiles = "id,email,first_name,last_name";
        Map<String, String> params = new HashMap();
        params.put("fields", listFiles);
        params.put("access_token", token);
        return restTemplate.getForObject(
                FacebookAPIConstant.FB_USER_PROFILE_URL_TEMPLATE,
                UserFacebookDTO.class,
                params);
    }

    private FacebookAccessToken exchangeForLongLivedToken(AccessTokenDTO shortLivedToken) {
        Map<String, String> params = new HashMap();
        params.put("client_id", appId);
        params.put("client_secret", appSecret);
        params.put("fb_exchange_token", shortLivedToken.getToken());
        return restTemplate.getForObject(
                FacebookAPIConstant.FB_EXCHANGE_TOKEN_URL_TEMPLATE,
                FacebookAccessToken.class,
                params);
    }

}
