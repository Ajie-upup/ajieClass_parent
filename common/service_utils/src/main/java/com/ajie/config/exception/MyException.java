package com.ajie.config.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author:hyj
 * @date:2022/10/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyException extends RuntimeException {
    private Integer code;
    private String msg;
}
