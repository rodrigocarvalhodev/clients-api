package net.rodrigocarvalho.clientsapi.mapper.response;

import net.rodrigocarvalho.clientsapi.model.response.AppUserResponse;
import net.rodrigocarvalho.clientsapi.persistance.entity.AppUser;
import net.rodrigocarvalho.clientsapi.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AppUserResponseMapper implements Mapper<AppUser, AppUserResponse> {

    @Override
    public AppUserResponse map(AppUser user) {
        if (user == null) return null;
        var response = new AppUserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setPassword(user.getPassword());
        return response;
    }
}
