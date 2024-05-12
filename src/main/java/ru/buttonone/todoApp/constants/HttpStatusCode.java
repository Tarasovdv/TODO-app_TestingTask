package ru.buttonone.todoApp.constants;

import lombok.Getter;

@Getter
public enum HttpStatusCode {
    SUCCESS_REQ(200),
    SUCCESS_CREATE(201),
    SUCCESS_DELETE(204),
    INCORRECT_INPUT(400),
    UNSUPPORT_TYPE(415);

    private final int code;

    HttpStatusCode(int code) {
        this.code = code;
    }
}