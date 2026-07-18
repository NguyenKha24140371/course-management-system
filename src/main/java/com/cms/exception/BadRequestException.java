package com.cms.exception;

// Exception khi dữ liệu gửi lên không hợp lệ
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }

}