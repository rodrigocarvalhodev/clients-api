package net.rodrigocarvalho.clientsapi.service.impl;

import lombok.RequiredArgsConstructor;
import net.rodrigocarvalho.clientsapi.exception.UserExistsException;
import net.rodrigocarvalho.clientsapi.model.request.AppUserRequest;
import net.rodrigocarvalho.clientsapi.model.response.AppUserResponse;
import net.rodrigocarvalho.clientsapi.persistance.entity.AppUser;
import net.rodrigocarvalho.clientsapi.persistance.repository.AppUserRepository;
import net.rodrigocarvalho.clientsapi.service.AppUserService;
import net.rodrigocarvalho.clientsapi.mapper.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository repository;

    private final Mapper<AppUserRequest, AppUser> appUserRequestMapper;

    private final Mapper<AppUser, AppUserResponse> appUserResponseMapper;

    private final Mapper<AppUser, UserDetails> userDetailsMapper;

    @Override
    public AppUserResponse create(AppUserRequest request) {
        var exists = this.repository.existsByUsername(request.getUsername());
        if (exists)
            throw new UserExistsException(request.getUsername());

        var savedAppUser = this.repository.saveAndFlush(this.appUserRequestMapper.map(request));
        return this.appUserResponseMapper.map(savedAppUser);
    }

    @Override
    public Optional<AppUserResponse> get(Long id) {
        return this.repository.findById(id).map(appUserResponseMapper::map);
    }

    @Override
    public Optional<AppUserResponse> getByUsername(String username) {
        return this.repository.findByUsername(username).map(appUserResponseMapper::map);
    }

    @Override
    public Page<AppUserResponse> get(Pageable pageable) {
        var appUserList = repository.findAll(pageable);
        return appUserList.map(appUserResponseMapper::map);
    }

    @Override
    public Optional<AppUserResponse> update(String username, AppUserRequest request) {
        return this.repository.findByUsername(username)
                .map(result -> {
                    result.setPassword(request.getPassword());
                    this.repository.save(result);
                    return this.appUserResponseMapper.map(result);
                });
    }

    @Override
    public void delete(String username) {
        this.repository.deleteByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var appUser = this.repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Login not found"));
        return userDetailsMapper.map(appUser);
    }
}
