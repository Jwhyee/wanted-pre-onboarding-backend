package com.example.wanted.common.exception.runtime;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseRuntimeException {

    @Override
    public String getMessage() {
        return "조회된 데이터가 없습니다.";
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }

}
