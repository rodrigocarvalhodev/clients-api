package net.rodrigocarvalho.clientsapi.service.impl;

import lombok.RequiredArgsConstructor;
import net.rodrigocarvalho.clientsapi.model.request.ServiceProvidedRequest;
import net.rodrigocarvalho.clientsapi.model.response.ServiceProvidedResponse;
import net.rodrigocarvalho.clientsapi.persistance.entity.ServiceProvided;
import net.rodrigocarvalho.clientsapi.persistance.repository.ServiceProvidedRepository;
import net.rodrigocarvalho.clientsapi.mapper.Mapper;
import net.rodrigocarvalho.clientsapi.service.ServiceProvidedService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceProvidedServiceImpl implements ServiceProvidedService {

    private final ServiceProvidedRepository repository;

    private final Mapper<ServiceProvidedRequest, ServiceProvided> serviceProvidedRequestMapper;

    private final Mapper<ServiceProvided, ServiceProvidedResponse> serviceProvidedResponseMapper;

    @Override
    public ServiceProvidedResponse create(ServiceProvidedRequest request) {
        var serviceProvided = this.repository.saveAndFlush(this.serviceProvidedRequestMapper.map(request));
        return this.serviceProvidedResponseMapper.map(serviceProvided);
    }

    @Override
    public Optional<ServiceProvidedResponse> get(Long id) {
        return this.repository.findById(id).map(this.serviceProvidedResponseMapper::map);
    }

    @Override
    public List<ServiceProvidedResponse> getByClientNameAndMonth(String name, Integer month) {
        return this.repository.findByClientNameAndMonth("%" + name + "%", month).stream()
                .map(this.serviceProvidedResponseMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ServiceProvidedResponse> update(Long id, ServiceProvidedRequest request) {
        return this.repository.findById(id)
                .map(serviceProvided -> {
                    ServiceProvided provided = this.serviceProvidedRequestMapper.map(request);
                    provided.setId(id);
                    return this.serviceProvidedResponseMapper.map(this.repository.saveAndFlush(provided));
                });
    }

    @Override
    public void delete(Long id) {
        this.repository.deleteById(id);
    }
}
