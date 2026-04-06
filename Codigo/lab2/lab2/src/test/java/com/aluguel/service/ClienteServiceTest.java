package com.aluguel.service;

import com.aluguel.model.Cliente;
import com.aluguel.repository.ClienteRepository;
import com.aluguel.repository.ClienteRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ClienteService CRUD operations
 */
class ClienteServiceTest {

    private ClienteService clienteService;
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setup() {
        clienteRepository = new ClienteRepositoryImpl();
        clienteService = new ClienteService();
        clienteService.clienteRepository = clienteRepository;
    }

    @Test
    void testCriarCliente() {
        Cliente cliente = new Cliente("joao.silva", "senha123", "João Silva", 
                                     "Rua A, 123", "123456789", "987.654.321-00", "Engenheiro");
        
        Cliente criado = clienteService.criar(cliente);
        
        assertNotNull(criado.getId());
        assertEquals("joao.silva", criado.getLogin());
        assertEquals("João Silva", criado.getNome());
        assertEquals("987.654.321-00", criado.getCpf());
        assertEquals("Engenheiro", criado.getProfissao());
    }

    @Test
    void testObterCliente() {
        Cliente cliente = new Cliente("maria.santos", "senha456", "Maria Santos",
                                     "Rua B, 456", "987654321", "123.456.789-11", "Advogada");
        Cliente criado = clienteService.criar(cliente);
        
        var obtido = clienteService.obter(criado.getId());
        
        assertTrue(obtido.isPresent());
        assertEquals("Maria Santos", obtido.get().getNome());
        assertEquals("123.456.789-11", obtido.get().getCpf());
    }

    @Test
    void testListarClientes() {
        Cliente cliente1 = new Cliente("cliente1", "senha1", "Cliente Um",
                                      "Rua 1", "111111111", "111.111.111-11", "Profissão 1");
        Cliente cliente2 = new Cliente("cliente2", "senha2", "Cliente Dois",
                                      "Rua 2", "222222222", "222.222.222-22", "Profissão 2");
        
        clienteService.criar(cliente1);
        clienteService.criar(cliente2);
        
        var clientes = clienteService.listar();
        
        assertEquals(2, clientes.size());
    }

    @Test
    void testAtualizarCliente() {
        Cliente cliente = new Cliente("pedro", "senha", "Pedro Costa",
                                     "Rua C", "444444444", "333.333.333-33", "Técnico");
        Cliente criado = clienteService.criar(cliente);
        
        Cliente atualizado = new Cliente("pedro_novo", "senha_nova", "Pedro Costa Silva",
                                         "Rua D", "555555555", "333.333.333-33", "Engenheiro Técnico");
        
        var resultado = clienteService.atualizar(criado.getId(), atualizado);
        
        assertTrue(resultado.isPresent());
        assertEquals("Pedro Costa Silva", resultado.get().getNome());
        assertEquals("pedro_novo", resultado.get().getLogin());
        assertEquals("Engenheiro Técnico", resultado.get().getProfissao());
    }

    @Test
    void testDeletarCliente() {
        Cliente cliente = new Cliente("carlos", "senha", "Carlos Mendes",
                                     "Rua E", "666666666", "444.444.444-44", "Medico");
        Cliente criado = clienteService.criar(cliente);
        
        boolean deletado = clienteService.deletar(criado.getId());
        
        assertTrue(deletado);
        var obtido = clienteService.obter(criado.getId());
        assertFalse(obtido.isPresent());
    }

    @Test
    void testObterPorCpf() {
        Cliente cliente = new Cliente("ana", "senha", "Ana Silva",
                                     "Rua F", "777777777", "555.555.555-55", "Dentista");
        clienteService.criar(cliente);
        
        var obtido = clienteService.obterPorCpf("555.555.555-55");
        
        assertTrue(obtido.isPresent());
        assertEquals("Ana Silva", obtido.get().getNome());
        assertEquals("Dentista", obtido.get().getProfissao());
    }

    @Test
    void testCriarClienteComCpfDuplicado() {
        Cliente cliente1 = new Cliente("user1", "senha1", "User Um",
                                      "Rua 1", "111111111", "666.666.666-66", "Profissão");
        clienteService.criar(cliente1);
        
        Cliente cliente2 = new Cliente("user2", "senha2", "User Dois",
                                      "Rua 2", "222222222", "666.666.666-66", "Profissão");
        
        assertThrows(IllegalArgumentException.class, () -> clienteService.criar(cliente2));
    }
}
