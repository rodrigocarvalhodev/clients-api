package net.rodrigocarvalho.clientsapi.service;

import net.rodrigocarvalho.clientsapi.model.request.AppUserRequest;
import net.rodrigocarvalho.clientsapi.model.response.AppUserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface AppUserService extends UserDetailsService {

    AppUserResponse create(AppUserRequest request);
    Optional<AppUserResponse> get(Long id);

    Optional<AppUserResponse> getByUsername(String username);


    Page<AppUserResponse> get(Pageable pageable);

    Optional<AppUserResponse> update(String username, AppUserRequest request);

    void delete(String username);

}
