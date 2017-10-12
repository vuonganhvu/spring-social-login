package com.higgsup.oauth.config;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class FilterAuthenBefor extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       Boolean credentials = (Boolean) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        //TODO kiem tra xem co thong tin dang nhap hay chua
        if(credentials) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            throw new ServletException("Chua xac thuc");
        }
    }
}
