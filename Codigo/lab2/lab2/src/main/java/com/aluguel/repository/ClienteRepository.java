package com.aluguel.repository;

import com.aluguel.model.Cliente;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Cliente CRUD operations
 */
public interface ClienteRepository {

    /**
     * Save or update a client
     * @param cliente the client to save/update
     * @return the saved client
     */
    Cliente save(Cliente cliente);

    /**
     * Find a client by ID
     * @param id the client ID
     * @return Optional containing the client if found
     */
    Optional<Cliente> findById(Long id);

    /**
     * Find a client by CPF
     * @param cpf the client's CPF
     * @return Optional containing the client if found
     */
    Optional<Cliente> findByCpf(String cpf);

    /**
     * Get all clients
     * @return list of all clients
     */
    List<Cliente> findAll();

    /**
     * Update a client
     * @param id the client ID
     * @param cliente the updated client data
     * @return Optional containing the updated client
     */
    Optional<Cliente> update(Long id, Cliente cliente);

    /**
     * Delete a client
     * @param id the client ID
     * @return true if deletion was successful
     */
    boolean delete(Long id);

    /**
     * Check if a client exists
     * @param id the client ID
     * @return true if client exists
     */
    boolean existsById(Long id);
}
