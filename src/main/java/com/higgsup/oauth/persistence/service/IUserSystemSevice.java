package com.higgsup.oauth.persistence.service;

import com.higgsup.oauth.persistence.dto.AccessTokenDTO;
import com.higgsup.oauth.persistence.dto.UserSystemDTO;
import com.higgsup.oauth.persistence.model.UserSystem;

public interface IUserSystemSevice {
    Long save(UserSystem userSystem);
    UserSystemDTO login(AccessTokenDTO accessTokenDTO);
}
