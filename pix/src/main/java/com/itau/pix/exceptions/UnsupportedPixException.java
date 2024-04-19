package com.itau.pix.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnsupportedPixException extends RuntimeException {

    public UnsupportedPixException(String exception) {
        super(exception);
    }


}
