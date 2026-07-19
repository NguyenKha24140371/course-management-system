package com.cms.exception;

// Exception khi không tìm thấy dữ liệu
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

}