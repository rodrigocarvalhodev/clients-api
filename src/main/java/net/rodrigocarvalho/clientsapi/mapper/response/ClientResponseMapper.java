package net.rodrigocarvalho.clientsapi.mapper.response;

import net.rodrigocarvalho.clientsapi.model.response.ClientResponse;
import net.rodrigocarvalho.clientsapi.persistance.entity.Client;
import net.rodrigocarvalho.clientsapi.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ClientResponseMapper implements Mapper<Client, ClientResponse> {

    @Override
    public ClientResponse map(Client client) {
        if (client == null) return null;
        var response = new ClientResponse();
        response.setId(client.getId());
        response.setCpf(client.getCpf());
        response.setName(client.getName());
        response.setRegistredTime(client.getRegistredTime());
        return response;
    }
}
