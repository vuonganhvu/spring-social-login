package com.higgsup.oauth.persistence.api;

import com.higgsup.oauth.config.UserSystemAuthenToken;
import com.higgsup.oauth.persistence.ResponeCommon;
import com.higgsup.oauth.persistence.dto.AccessTokenDTO;
import com.higgsup.oauth.persistence.dto.UserSystemDTO;
import com.higgsup.oauth.persistence.model.UserSystem;
import com.higgsup.oauth.persistence.service.IUserSystemSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/famo")
public class TestLoginApi {

    @Autowired
    private IUserSystemSevice userSystemSevice;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public ResponeCommon<UserSystemDTO> login(@RequestBody AccessTokenDTO accessTokenDTO) {
        ResponeCommon<UserSystemDTO> rest = new ResponeCommon<UserSystemDTO>();
        try {
            UserSystemDTO userSystemDTO = userSystemSevice.login(accessTokenDTO);
            UserSystemAuthenToken userAuthenToken = new UserSystemAuthenToken(userSystemDTO,true);
            SecurityContextHolder.getContext().setAuthentication(userAuthenToken);
            rest.setCode("OK");
            rest.setObject(userSystemDTO);
        } catch (Exception e) {
            rest.setCode("NOT_OK");
        }
        return rest;
    }

    @RequestMapping(value = "/getInfo", method = RequestMethod.GET)
    public String getInfo() {
        try {
            UserSystemDTO userSystemDTO = (UserSystemDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            System.out.println(userSystemDTO.getId());
        } catch (Exception e) {
            return e.getMessage();
        }
        return "OK";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponeCommon<String> logout() {
        ResponeCommon<String> rest = new ResponeCommon<String>();
        try {
            //reset lai context
            SecurityContextHolder.getContext().setAuthentication(null);
            rest.setCode("OK");
        } catch (Exception e) {
            rest.setCode("NOT_OK");
            rest.setObject(e.getMessage());
        }
        return rest;
    }

}
