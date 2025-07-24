package repository;

import model.Cliente; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors; 

public class ClienteRepository {

    //HashMap para armazenar os clientes, onde a chave é o ID do cliente
    private Map<String, Cliente> clientes = new HashMap<>();


    public void adicionarCliente(Cliente cliente) {
        if (cliente != null) {
            if (clientes.containsKey(cliente.getId())) {
                System.out.println("Erro: Cliente com ID '" + cliente.getId() + "' já existe no repositório.");
            } else {
                
                boolean cpfJaExiste = clientes.values().stream()
                                            .anyMatch(c -> c.getCpf().equals(cliente.getCpf()));
                if (cpfJaExiste) {
                    System.out.println("Erro: Cliente com CPF '" + cliente.getCpf() + "' já existe no repositório.");
                } else {
                    clientes.put(cliente.getId(), cliente);
                    System.out.println("Cliente '" + cliente.getNome() + "' (ID: " + cliente.getId() + ") adicionado ao repositório.");
                }
            }
        } else {
            System.out.println("Erro: Não foi possível adicionar cliente nulo.");
        }
    }


    public Cliente buscarClientePorId(String id) {
        return clientes.get(id); 
    }


    public Cliente buscarClientePorCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            return null;
        }
   
        return clientes.values().stream()
                       .filter(cliente -> cliente.getCpf().equals(cpf))
                       .findFirst()
                       .orElse(null); 
    }


    public List<Cliente> buscarClientePorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return new ArrayList<>(); 
        }
        String nomeLowerCase = nome.toLowerCase();
        return clientes.values().stream()
                       .filter(cliente -> cliente.getNome().toLowerCase().contains(nomeLowerCase))
                       .collect(Collectors.toList());
    }


    public List<Cliente> listarTodosClientes() {
        return new ArrayList<>(clientes.values());
    }


    public boolean removerCliente(String id) {
        if (clientes.containsKey(id)) {
            Cliente clienteRemovido = clientes.remove(id);
            System.out.println("Cliente '" + clienteRemovido.getNome() + "' (ID: " + id + ") removido do repositório.");
            return true;
        } else {
            System.out.println("Erro: Cliente com ID '" + id + "' não encontrado para remoção.");
            return false;
        }
    }

    public boolean atualizarCliente(Cliente clienteAtualizado) {
        if (clienteAtualizado != null && clientes.containsKey(clienteAtualizado.getId())) {
            clientes.put(clienteAtualizado.getId(), clienteAtualizado); // Sobrescreve o cliente existente
            System.out.println("Cliente '" + clienteAtualizado.getNome() + "' (ID: " + clienteAtualizado.getId() + ") atualizado no repositório.");
            return true;
        } else if (clienteAtualizado != null) {
            System.out.println("Erro: Cliente com ID '" + clienteAtualizado.getId() + "' não encontrado para atualização.");
            return false;
        } else {
            System.out.println("Erro: Não foi possível atualizar cliente nulo.");
            return false;
        }
    }


    public int contarClientes() {
        return clientes.size();    // Retorna o número de clientes no repositório
    }
}