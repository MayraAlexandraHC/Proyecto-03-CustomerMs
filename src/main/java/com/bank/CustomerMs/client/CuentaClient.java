package com.bank.CustomerMs.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class CuentaClient {
    private final RestTemplate restTemplate;

    public boolean tieneCuentasActivas(String clienteId) {
        try {
            // Obtener la lista de cuentas del cliente
            String url = "/cuentas?clienteId=" + clienteId;
            List<Map<String, Object>> cuentas = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Map<String, Object>>>() {}
            ).getBody();

            // Verificar si hay cuentas
            return cuentas != null && !cuentas.isEmpty();
        } catch (Exception e) {
            log.error("Error al verificar cuentas activas del cliente: {}", e.getMessage());
            throw new RuntimeException("Error al verificar cuentas activas del cliente");
        }
    }
}