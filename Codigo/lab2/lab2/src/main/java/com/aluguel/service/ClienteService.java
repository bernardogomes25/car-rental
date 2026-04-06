package com.aluguel.service;

import com.aluguel.model.Cliente;
import com.aluguel.repository.ClienteRepository;
import io.micronaut.context.annotation.Prototype;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Service layer for Cliente CRUD operations
 */
@Prototype
public class ClienteService {

    @Inject
    protected ClienteRepository clienteRepository;

    /**
     * Create a new client
     * @param cliente the client to create
     * @return the created client
     */
    public Cliente criar(Cliente cliente) {
        if (cliente.getCpf() != null && clienteRepository.findByCpf(cliente.getCpf()).isPresent()) {
            throw new IllegalArgumentException("CPF already exists");
        }
        return clienteRepository.save(cliente);
    }

    /**
     * Get a client by ID
     * @param id the client ID
     * @return Optional containing the client
     */
    public Optional<Cliente> obter(Long id) {
        return clienteRepository.findById(id);
    }

    /**
     * Get all clients
     * @return list of all clients
     */
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    /**
     * Update a client
     * @param id the client ID
     * @param cliente the updated client data
     * @return Optional containing the updated client
     */
    public Optional<Cliente> atualizar(Long id, Cliente cliente) {
        if (!clienteRepository.existsById(id)) {
            return Optional.empty();
        }
        
        // If CPF is being updated, check for duplicates
        Optional<Cliente> existente = clienteRepository.findById(id);
        if (existente.isPresent() && cliente.getCpf() != null &&
            !cliente.getCpf().equals(existente.get().getCpf())) {
            if (clienteRepository.findByCpf(cliente.getCpf()).isPresent()) {
                throw new IllegalArgumentException("CPF already exists");
            }
        }
        
        return clienteRepository.update(id, cliente);
    }

    /**
     * Delete a client
     * @param id the client ID
     * @return true if deletion was successful
     */
    public boolean deletar(Long id) {
        return clienteRepository.delete(id);
    }

    /**
     * Get a client by CPF
     * @param cpf the client's CPF
     * @return Optional containing the client
     */
    public Optional<Cliente> obterPorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }
}
