package com.higgsup.oauth.persistence.service;

import com.higgsup.oauth.persistence.api.FacebookAccessToken;
import com.higgsup.oauth.persistence.dto.AccessTokenDTO;
import com.higgsup.oauth.persistence.dto.UserFacebookDTO;
import com.higgsup.oauth.persistence.dto.UserSystemDTO;
import com.higgsup.oauth.persistence.model.UserSystem;
import com.higgsup.oauth.persistence.model.UserToken;
import com.higgsup.oauth.persistence.repo.UserSystemRepository;
import com.higgsup.oauth.persistence.repo.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSystemService implements IUserSystemSevice {

    @Autowired
    private UserSystemRepository userSystemRepository;
    @Autowired
    private IFacebookApiService accessTokenService;
    @Autowired
    private UserTokenRepository userTokenRepository;

    @Override
    public Long save(UserSystem userSystem) {
        userSystemRepository.save(userSystem);
        return userSystem.getId();
    }

    @Override
    public UserSystemDTO login(AccessTokenDTO accessTokenDTO) {
        UserFacebookDTO user = accessTokenService.getProfile(accessTokenDTO.getToken());
        Long userSystemId = userTokenRepository.getUserSystemId(String.valueOf(user.getId()), "facebook" );
        if(userSystemId == null){
            UserSystem userSystem = new UserSystem();
            userSystem.setEmail(user.getEmail());
            userSystem.setFirstName(user.getFirst_name());
            userSystem.setLastName(user.getLast_name());
            userSystem.setStatus("0");
            userSystem.setUserName(user.getName());
            userSystemRepository.save(userSystem);
            userSystemId = userSystem.getId();
        }
        accessTokenDTO.setProviderId("facebook");
        accessTokenDTO.setPrviderUserId(String.valueOf(user.getId()));
        accessTokenDTO.setUserSystemId(Long.valueOf(userSystemId));
        FacebookAccessToken facebookAccessToken = accessTokenService.exchange(accessTokenDTO);
        UserSystemDTO userSystemDTO = new UserSystemDTO();
        userSystemDTO.setAccessToken(facebookAccessToken.getAccessToken());
        userSystemDTO.setUserName(user.getName());
        userSystemDTO.setId(Long.valueOf(userSystemId));
        userSystemDTO.setProviderId(accessTokenDTO.getProviderId());
        userSystemDTO.setProviderUserId(user.getId());
        return userSystemDTO;
    }
}
