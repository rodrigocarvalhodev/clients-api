package net.rodrigocarvalho.clientsapi.mapper.request;

import net.rodrigocarvalho.clientsapi.model.request.ClientRequest;
import net.rodrigocarvalho.clientsapi.persistance.entity.Client;
import net.rodrigocarvalho.clientsapi.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ClientRequestMapper implements Mapper<ClientRequest, Client> {

    @Override
    public Client map(ClientRequest request) {
        if (request == null) return null;
        var client = new Client();
        client.setName(request.getName());
        client.setCpf(request.getCpf());
        client.setRegistredTime(request.getRegistredTime());
        return client;
    }
}
