package net.rodrigocarvalho.clientsapi.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import net.rodrigocarvalho.clientsapi.exception.UserExistsException;
import net.rodrigocarvalho.clientsapi.model.request.AppUserRequest;
import net.rodrigocarvalho.clientsapi.model.response.AppUserResponse;
import net.rodrigocarvalho.clientsapi.persistance.entity.AppUser;
import net.rodrigocarvalho.clientsapi.service.AppUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService service;

    @ApiOperation("API responsável pela criação de usuário.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Criou um usuário!"),
            @ApiResponse(code = 400, message = "O usuário já existe."),
            @ApiResponse(code = 500, message = "Ocorreu uma exceção ao criar o usuário")
        }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AppUserResponse> create(@RequestBody @Valid AppUserRequest request) {
        try {
            return ResponseEntity.ok(service.create(request));
        } catch (UserExistsException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @ApiOperation("API responsável pela busca de usuário pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Encontrou um usuário!"),
            @ApiResponse(code = 404, message = "O usuário não foi encontrado"),
            @ApiResponse(code = 500, message = "Ocorreu uma exceção ao buscar o usuário")
        }
    )
    @GetMapping("{username}")
    public ResponseEntity<AppUserResponse> get(@PathVariable String username) {
        return service.getByUsername(username)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with username " + username + " not found"));
    }

    @ApiOperation("API responsável pela busca de usuário por paginação.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Encontrou usuários!"),
            @ApiResponse(code = 500, message = "Ocorreu uma exceção ao buscar os usuários")
        }
    )
    @GetMapping("{page}/{size}")
    public Page<AppUserResponse> getAll(
            @PathVariable Integer page,
            @PathVariable Integer size) {
        var pageRequest = PageRequest.of(page, size);
        return service.get(pageRequest);
    }

    @ApiOperation("API responsável pela atualização do usuário pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atualizou o usuário!"),
            @ApiResponse(code = 404, message = "Usuário não encontrado."),
            @ApiResponse(code = 500, message = "Ocorreu uma exceção ao atualizar o usuário")
        }
    )
    @PutMapping("{username}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AppUserResponse> update(@PathVariable String username,
                       @RequestBody @Valid AppUserRequest request) {
        return this.service.update(username, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @ApiOperation("API responsável pela atualização do usuário pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "deletou o usuário!"),
            @ApiResponse(code = 500, message = "Ocorreu uma exceção ao deletar o usuário")
        }
    )
    @DeleteMapping("{username}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String username) {
        service.delete(username);
    }
}
