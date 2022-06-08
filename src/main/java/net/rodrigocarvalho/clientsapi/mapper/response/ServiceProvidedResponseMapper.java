package net.rodrigocarvalho.clientsapi.mapper.response;

import lombok.RequiredArgsConstructor;
import net.rodrigocarvalho.clientsapi.model.response.ClientResponse;
import net.rodrigocarvalho.clientsapi.model.response.ServiceProvidedResponse;
import net.rodrigocarvalho.clientsapi.persistance.entity.Client;
import net.rodrigocarvalho.clientsapi.persistance.entity.ServiceProvided;
import net.rodrigocarvalho.clientsapi.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServiceProvidedResponseMapper implements Mapper<ServiceProvided, ServiceProvidedResponse> {

    private final Mapper<Client, ClientResponse> clientResponseMapper;

    @Override
    public ServiceProvidedResponse map(ServiceProvided serviceProvided) {
        if (serviceProvided == null) return null;
        var clientResponse = clientResponseMapper.map(serviceProvided.getClient());

        var response = new ServiceProvidedResponse();
        response.setId(serviceProvided.getId());
        response.setClient(clientResponse);
        response.setDescription(serviceProvided.getDescription());
        response.setValue(serviceProvided.getValue());
        response.setProvidedTime(serviceProvided.getProvidedTime());
        return response;
    }
}
