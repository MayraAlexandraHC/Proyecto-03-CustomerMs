package com.bank.CustomerMs.service;

import com.bank.CustomerMs.model.Cliente;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClienteService {
    /**
     * Crea un nuevo cliente
     * @param cliente Datos del cliente a crear
     * @return Cliente creado
     */
    Cliente crearCliente(Cliente cliente);

    /**
     * Lista todos los clientes
     * @return Lista de clientes
     */
    List<Cliente> listarClientes();

    /**
     * Obtiene un cliente por su ID
     * @param id Identificador del cliente
     * @return Cliente encontrado
     */
    Cliente obtenerClientePorId(String id);

    /**
     * Actualiza la información de un cliente
     * @param id Identificador del cliente
     * @param cliente Datos actualizados del cliente
     * @return Cliente actualizado
     */
    Cliente actualizarCliente(String id, Cliente cliente);

    /**
     * Elimina un cliente por su ID
     * @param id Identificador del cliente
     */
    void eliminarCliente(String id);

}