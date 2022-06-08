package net.rodrigocarvalho.clientsapi.mapper.request;

import net.rodrigocarvalho.clientsapi.model.request.AppUserRequest;
import net.rodrigocarvalho.clientsapi.persistance.entity.AppUser;
import net.rodrigocarvalho.clientsapi.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AppUserRequestMapper implements Mapper<AppUserRequest, AppUser> {

    @Override
    public AppUser map(AppUserRequest request) {
        if (request == null) return null;
        var user = new AppUser();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        return user;
    }
}
