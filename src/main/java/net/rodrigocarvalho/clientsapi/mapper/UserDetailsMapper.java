package net.rodrigocarvalho.clientsapi.mapper;

import net.rodrigocarvalho.clientsapi.persistance.entity.AppUser;
import net.rodrigocarvalho.clientsapi.mapper.Mapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsMapper implements Mapper<AppUser, UserDetails> {

    @Override
    public UserDetails map(AppUser appUser) {
        return User.
                builder().
                username(appUser.getUsername()).password(appUser.getPassword())
                .roles("USER")
                .build();
    }
}
