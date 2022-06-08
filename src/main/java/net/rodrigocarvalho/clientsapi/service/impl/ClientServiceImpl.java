package net.rodrigocarvalho.clientsapi.service.impl;

import lombok.RequiredArgsConstructor;
import net.rodrigocarvalho.clientsapi.model.request.ClientRequest;
import net.rodrigocarvalho.clientsapi.model.response.ClientResponse;
import net.rodrigocarvalho.clientsapi.persistance.entity.Client;
import net.rodrigocarvalho.clientsapi.persistance.repository.ClientRepository;
import net.rodrigocarvalho.clientsapi.service.ClientService;
import net.rodrigocarvalho.clientsapi.mapper.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;

    private final Mapper<ClientRequest, Client> clientRequestMapper;

    private final Mapper<Client, ClientResponse> clientResponseMapper;

    @Override
    public ClientResponse create(ClientRequest request) {
        var client = this.repository.saveAndFlush(this.clientRequestMapper.map(request));
        return this.clientResponseMapper.map(client);
    }

    @Override
    public Optional<ClientResponse> get(Long id) {
        return this.repository.findById(id).map(clientResponseMapper::map);
    }

    @Override
    public Page<ClientResponse> get(Pageable pageable) {
        var clientsList = repository.findAll(pageable);
        return clientsList.map(clientResponseMapper::map);
    }

    @Override
    public Optional<ClientResponse> update(Long id, ClientRequest request) {
        return this.repository.findById(id)
                .map(client -> {
                    var newClient = this.clientRequestMapper.map(request);
                    newClient.setId(id);
                    return this.clientResponseMapper.map(this.repository.saveAndFlush(newClient));
                });
    }

    @Override
    public void delete(Long id) {
        this.repository.deleteById(id);
    }
}
