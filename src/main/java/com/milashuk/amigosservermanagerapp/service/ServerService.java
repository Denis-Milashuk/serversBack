package com.milashuk.amigosservermanagerapp.service;

import com.milashuk.amigosservermanagerapp.domain.Server;

import java.util.List;

public interface ServerService {
    Server create (Server server);
    List<Server> list (int limit);
    Server getById (Long id);
    Server update (Server server);
    Boolean deleteById (Long id);
    Server ping (String ipAddress);
}
