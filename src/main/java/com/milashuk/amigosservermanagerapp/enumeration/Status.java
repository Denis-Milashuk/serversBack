package com.milashuk.amigosservermanagerapp.enumeration;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Status {
    SEVER_UP("SERVER_UP"),
    SERVER_DOWN("SERVER_DOWN");

    private final String status;
}
