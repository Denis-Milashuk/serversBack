package com.milashuk.amigosservermanagerapp;

import com.milashuk.amigosservermanagerapp.domain.Server;
import com.milashuk.amigosservermanagerapp.enumeration.Status;
import com.milashuk.amigosservermanagerapp.repo.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AmigosServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmigosServerApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ServerRepository serverRepository) {
        return args -> {
            serverRepository.save(new Server(null, "192.168.1.160", "Ubuntu Linux", "16 GB", "PC", "http://localhost:8080/servers/image/server1.png", Status.SEVER_UP));
            serverRepository.save(new Server(null, "192.168.1.58", "Ubuntu Linux", "16 GB", "PC", "http://localhost:8080/servers/image/server2.png", Status.SEVER_UP));
            serverRepository.save(new Server(null, "192.168.1.35", "Ubuntu Linux", "16 GB", "PC", "http://localhost:8080/servers/image/server3.png", Status.SERVER_DOWN));
        };
    }
}
