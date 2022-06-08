package net.rodrigocarvalho.clientsapi.service;

import net.rodrigocarvalho.clientsapi.model.request.ServiceProvidedRequest;
import net.rodrigocarvalho.clientsapi.model.response.ServiceProvidedResponse;

import java.util.List;
import java.util.Optional;

public interface ServiceProvidedService {

    ServiceProvidedResponse create(ServiceProvidedRequest request);
    Optional<ServiceProvidedResponse> get(Long id);

    List<ServiceProvidedResponse> getByClientNameAndMonth(String name, Integer month);

    Optional<ServiceProvidedResponse> update(Long id, ServiceProvidedRequest request);
    void delete(Long id);

}
