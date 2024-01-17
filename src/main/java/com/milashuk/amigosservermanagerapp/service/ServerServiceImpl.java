package com.milashuk.amigosservermanagerapp.service;

import com.milashuk.amigosservermanagerapp.domain.Server;
import com.milashuk.amigosservermanagerapp.enumeration.Status;
import com.milashuk.amigosservermanagerapp.repo.ServerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ServerServiceImpl implements ServerService{

    private final ServerRepository serverRepository;

    @Override
    public Server create(Server server) {
        log.info("Saving server: {}", server.getName());
        server.setImageUrl(this.setServerImageUrl());
        return this.serverRepository.save(server);
    }

    @Override
    public List<Server> list(int limit) {
        log.info("Fetching all servers");
        return this.serverRepository.findAll(PageRequest.ofSize(limit)).toList();
    }

    @Override
    public Server getById(Long id) {
        log.info("Fetching server by id: {}", id);
        return this.serverRepository.getReferenceById(id);
    }

    @Override
    public Server update(Server server) {
        log.info("Updating server: {}", server);
        return this.serverRepository.save(server);
    }

    @Override
    public Boolean deleteById(Long id) {
        log.info("Deleting server by id: {}", id);
        this.serverRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Server ping(String ipAddress) {
        log.info("Pinging server IP: {}", ipAddress);
        Optional<Server> serverOptional = serverRepository.findByIpAddress(ipAddress);
        Server server = serverOptional.orElseThrow();
        try {
            InetAddress inetAddress = InetAddress.getByName(ipAddress);
            server.setStatus(inetAddress.isReachable(10000) ? Status.SEVER_UP : Status.SERVER_DOWN);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.serverRepository.save(server);
        return server;
    }

    private String setServerImageUrl() {
        String [] imageNames = {"server1.png", "server2.png", "server3.png"};
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/images/" + imageNames[new Random().nextInt(3)]).toUriString();
    }
}
