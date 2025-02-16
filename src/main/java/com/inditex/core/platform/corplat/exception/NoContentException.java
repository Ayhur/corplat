package com.inditex.core.platform.corplat.exception;

import lombok.Getter;

@Getter
public class NoContentException extends RuntimeException {

    private final String message;

    public NoContentException(final String message) {
        this.message = message;
    }

}
