package net.rodrigocarvalho.clientsapi.service;

import net.rodrigocarvalho.clientsapi.model.request.ClientRequest;
import net.rodrigocarvalho.clientsapi.model.response.ClientResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ClientService {

    ClientResponse create(ClientRequest request);
    Optional<ClientResponse> get(Long id);

    Page<ClientResponse> get(Pageable pageable);
    Optional<ClientResponse> update(Long id, ClientRequest request);
    void delete(Long id);

}