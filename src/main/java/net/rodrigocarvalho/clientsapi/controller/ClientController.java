package net.rodrigocarvalho.clientsapi.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import net.rodrigocarvalho.clientsapi.model.request.ClientRequest;
import net.rodrigocarvalho.clientsapi.model.response.ClientResponse;
import net.rodrigocarvalho.clientsapi.persistance.entity.Client;
import net.rodrigocarvalho.clientsapi.persistance.repository.ClientRepository;
import net.rodrigocarvalho.clientsapi.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;

    @ApiOperation("API responsável pela criação de um cliente.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Criou um cliente!"),
            @ApiResponse(code = 500, message = "Ocorreu uma exceção ao criar o cliente")
        }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClientResponse> create(@RequestBody @Valid ClientRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @ApiOperation("API responsável pela busca de um cliente pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Busca realizada com sucesso!"),
            @ApiResponse(code = 404, message = "Cliente não encontrado."),
            @ApiResponse(code = 500, message = "Ocorreu uma exceção ao buscar o cliente")
        }
    )
    @GetMapping("{id}")
    public ResponseEntity<ClientResponse> get(@PathVariable Long id) {
        return service.get(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client with id " + id + " not found"));
    }

    @ApiOperation("API responsável pela busca de clientes por paginação.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Busca realizada com sucesso!"),
            @ApiResponse(code = 500, message = "Ocorreu uma exceção ao buscar clientes")
        }
    )
    @GetMapping("{page}/{size}")
    public Page<ClientResponse> get(
            @PathVariable Integer page,
            @PathVariable Integer size
    ) {
        var pageRequest = PageRequest.of(page, size);
        return service.get(pageRequest);
    }

    @ApiOperation("API responsável pela atualização de um cliente.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente atualizado com sucesso!"),
            @ApiResponse(code = 404, message = "Cliente não encontrado."),
            @ApiResponse(code = 500, message = "Ocorreu uma exceção ao atualizar o cliente")
        }
    )
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ClientResponse> update(
                        @PathVariable Long id,
                        @RequestBody @Valid ClientRequest request) {
        return this.service.update(id, request)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client with id " + id + " not found"));
    }

    @ApiOperation("API responsável pela deleção de um cliente.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente deletado com sucesso!"),
            @ApiResponse(code = 500, message = "Ocorreu uma exceção ao deletar o cliente")
        }
    )
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        this.service.delete(id);
    }
}