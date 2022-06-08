package net.rodrigocarvalho.clientsapi.mapper.request;

import lombok.RequiredArgsConstructor;
import net.rodrigocarvalho.clientsapi.model.request.ServiceProvidedRequest;
import net.rodrigocarvalho.clientsapi.persistance.entity.ServiceProvided;
import net.rodrigocarvalho.clientsapi.persistance.repository.ClientRepository;
import net.rodrigocarvalho.clientsapi.mapper.Mapper;
import net.rodrigocarvalho.clientsapi.util.BigDecimalConverter;
import net.rodrigocarvalho.clientsapi.util.LocalDateConverter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServiceProvidedRequestMapper implements Mapper<ServiceProvidedRequest, ServiceProvided> {

    private final BigDecimalConverter DECIMAL_CONVERTER = new BigDecimalConverter();
    private final LocalDateConverter LOCAL_DATE_CONVERTER = new LocalDateConverter();

    private final ClientRepository clientRepository;

    @Override
    public ServiceProvided map(ServiceProvidedRequest request) {
        if (request == null) return null;
        var client = clientRepository.findById(request.getClientId()).orElseThrow(() -> new RuntimeException("Client not found"));

        var providedTime = LOCAL_DATE_CONVERTER.convert(request.getProvidedTime());
        var value = DECIMAL_CONVERTER.convert(request.getValue());

        var service = new ServiceProvided();
        service.setDescription(request.getDescription());
        service.setClient(client);
        service.setProvidedTime(providedTime);
        service.setValue(value);
        return service;
    }
}
