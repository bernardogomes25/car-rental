package com.aluguel.controller;

import com.aluguel.model.Cliente;
import com.aluguel.service.ClienteService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * REST Controller for Cliente CRUD operations
 */
@Controller("/clientes")
public class ClienteController {

    @Inject
    private ClienteService clienteService;

    /**
     * Create a new client
     * POST /clientes
     */
    @Post
    public HttpResponse<Cliente> criar(@Body Cliente cliente) {
        try {
            Cliente novoCliente = clienteService.criar(cliente);
            return HttpResponse.created(novoCliente, URI.create("/clientes/" + novoCliente.getId()));
        } catch (IllegalArgumentException e) {
            return HttpResponse.badRequest();
        }
    }

    /**
     * Get a client by ID
     * GET /clientes/{id}
     */
    @Get("/{id}")
    public HttpResponse<Cliente> obter(Long id) {
        Optional<Cliente> cliente = clienteService.obter(id);
        return cliente.map(HttpResponse::ok)
                      .orElse(HttpResponse.notFound());
    }

    /**
     * Get all clients
     * GET /clientes
     */
    @Get
    public HttpResponse<List<Cliente>> listar() {
        List<Cliente> clientes = clienteService.listar();
        return HttpResponse.ok(clientes);
    }

    /**
     * Update a client
     * PUT /clientes/{id}
     */
    @Put("/{id}")
    public HttpResponse<Cliente> atualizar(Long id, @Body Cliente cliente) {
        try {
            Optional<Cliente> atualizado = clienteService.atualizar(id, cliente);
            return atualizado.map(HttpResponse::ok)
                            .orElse(HttpResponse.notFound());
        } catch (IllegalArgumentException e) {
            return HttpResponse.badRequest();
        }
    }

    /**
     * Delete a client
     * DELETE /clientes/{id}
     */
    @Delete("/{id}")
    public HttpResponse<Void> deletar(Long id) {
        if (clienteService.deletar(id)) {
            return HttpResponse.noContent();
        }
        return HttpResponse.notFound();
    }

    /**
     * Get a client by CPF
     * GET /clientes/cpf/{cpf}
     */
    @Get("/cpf/{cpf}")
    public HttpResponse<Cliente> obterPorCpf(String cpf) {
        Optional<Cliente> cliente = clienteService.obterPorCpf(cpf);
        return cliente.map(HttpResponse::ok)
                      .orElse(HttpResponse.notFound());
    }
}
