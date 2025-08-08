package com.tomorrowproject.restaurante_api.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerAdvancedConfig {

    @Value("${server.port:8080}")
    private String serverPort;

    @Bean
    public OpenAPI customOpenAPIWithServers() {
        return new OpenAPI()
                .addServersItem(new Server()
                        .url("http://localhost:" + serverPort)
                        .description("🏠 Servidor Local"))
                .addServersItem(new Server()
                        .url("https://github.com/p1lloww/Spring-Boot-UFBA-projeto.git")
                        .description("☁️ Servidor de Produção"))
                .info(new Info()
                        .title("🍽️ API de Gestão de Restaurante")
                        .description("""
                        ## 📖 Documentação da API
                        
                        Esta API permite gerenciar:
                        
                        - 📂 **Categorias** - Organização do cardápio
                        - 🍕 **Pratos** - Itens do cardápio com preços e descrições  
                        - 👤 **Clientes** - Cadastro e histórico de clientes
                        - 📝 **Pedidos** - Sistema completo de pedidos com status
                        
                        ### 🚀 Como usar:
                        1. Cadastre categorias primeiro
                        2. Adicione pratos às categorias
                        3. Registre clientes
                        4. Crie pedidos vinculando clientes e pratos
                        5. Acompanhe o status dos pedidos
                        
                        ### 🔄 Status de Pedidos:
                        - RECEBIDO → PREPARANDO → PRONTO → ENTREGUE → FINALIZADO
                        - CANCELADO (possível até PREPARANDO)
                        """)
                        .version("v1.0.0"));
    }
}