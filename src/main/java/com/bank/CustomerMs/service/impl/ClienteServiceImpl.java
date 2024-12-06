package com.bank.CustomerMs.service.impl;

import com.bank.CustomerMs.client.CuentaClient;
import com.bank.CustomerMs.model.Cliente;
import com.bank.CustomerMs.repository.ClienteRepository;
import com.bank.CustomerMs.service.ClienteService;
import com.bank.CustomerMs.exception.ClienteException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;
    private final CuentaClient cuentaClient;

    @Override
    @Transactional
    public Cliente crearCliente(Cliente cliente) {
        // Validar DNI único
        if (clienteRepository.existsByDni(cliente.getDni())) {
            throw new ClienteException("Ya existe un cliente con este DNI");
        }

        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente obtenerClientePorId(String id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteException("Cliente no encontrado"));
    }

    @Override
    @Transactional
    public Cliente actualizarCliente(String id, Cliente cliente) {
        // Buscar cliente existente
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteException("Cliente no encontrado"));

        // Validar DNI único si se está cambiando
        if (!clienteExistente.getDni().equals(cliente.getDni()) &&
            clienteRepository.existsByDni(cliente.getDni())) {
            throw new ClienteException("Ya existe un cliente con este DNI");
        }

        // Actualizar campos
        clienteExistente.setNombre(cliente.getNombre());
        clienteExistente.setApellido(cliente.getApellido());
        clienteExistente.setDni(cliente.getDni());
        clienteExistente.setEmail(cliente.getEmail());

        return clienteRepository.save(clienteExistente);
    }

    @Override
    @Transactional
    public void eliminarCliente(String id) {
        // Verificar existencia
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteException("Cliente no encontrado"));

        if (cuentaClient.tieneCuentasActivas(id)) {
            throw new ClienteException("No se puede eliminar el cliente porque tiene cuentas activas");
        }
        // (esta validación se hará con comunicación entre microservicios)
        clienteRepository.deleteById(id);
    }

}
