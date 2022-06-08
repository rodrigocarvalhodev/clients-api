package net.rodrigocarvalho.clientsapi.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import net.rodrigocarvalho.clientsapi.model.request.ServiceProvidedRequest;
import net.rodrigocarvalho.clientsapi.model.response.ServiceProvidedResponse;
import net.rodrigocarvalho.clientsapi.service.ServiceProvidedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/provided-services")
@RequiredArgsConstructor
public class ServiceProvidedController {

    private final ServiceProvidedService service;

    @ApiOperation("API responsável pela criação de um serviço prestado.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Criou um serviço prestado!"),
            @ApiResponse(code = 500, message = "Ocorreu uma exceção ao criar o serviço")
        }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ServiceProvidedResponse> create(@RequestBody @Valid ServiceProvidedRequest request) {
        return ResponseEntity.ok(this.service.create(request));
    }

    @ApiOperation("API responsável pela busca de serviços prestados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Busca realizada com sucesso!"),
            @ApiResponse(code = 500, message = "Ocorreu uma exceção ao buscar os serviços")
        }
    )
    @GetMapping
    public List<ServiceProvidedResponse> search(
            @RequestParam(value = "name", required = false, defaultValue = "") String name,
            @RequestParam(value = "month", required = false) Integer month
    ) {
        return this.service.getByClientNameAndMonth(name, month);
    }
}
