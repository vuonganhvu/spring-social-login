package com.higgsup.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.connect.*;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.GenericConnectionStatusView;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

import javax.sql.DataSource;

@Configuration
@EnableSocial
@ComponentScan(basePackages = { "com.higgsup.oauth" })
public class FacebookSocialConfigurer extends SocialAutoConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private Environment env;


    @Bean
    @ConditionalOnMissingBean({Facebook.class})
    @Scope(
            value = "request",
            proxyMode = ScopedProxyMode.INTERFACES
    )
    public Facebook facebook(ConnectionRepository repository) {
        Connection<Facebook> connection = repository.findPrimaryConnection(Facebook.class);
        return connection != null ? (Facebook)connection.getApi() : null;
    }

    @Bean(
            name = {"connect/facebookConnect", "connect/facebookConnected"}
    )
    @ConditionalOnProperty(
            prefix = "spring.social",
            name = {"auto-connection-views"}
    )
    public GenericConnectionStatusView facebookConnectView() {
        return new GenericConnectionStatusView("facebook", "Facebook");
    }

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        FacebookConnectionFactory facebookConnectionFactory = new FacebookConnectionFactory(env.getProperty("spring.social.facebook.appId"), env.getProperty("spring.social.facebook.appSecret"));
        facebookConnectionFactory.setScope("public_profile");
        return facebookConnectionFactory;
    }

    @Override
    public UserIdSource getUserIdSource() {
        //TODO lay thong tin userId de kiem tra ket noi facebook hay chua
        System.out.println("get UserIdSource");
        return new UserIdSource() {
            @Override
            public String getUserId() {

                UsernamePasswordAuthenticationToken userInfo = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
                System.out.println("userInfo "+ userInfo);
                return (String) userInfo.getPrincipal();
            }
        };
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        return repository;
    }

}
