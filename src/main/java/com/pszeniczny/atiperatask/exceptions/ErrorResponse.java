package com.pszeniczny.atiperatask.exceptions;

public record ErrorResponse(
        int status,
        String Message) {
}
