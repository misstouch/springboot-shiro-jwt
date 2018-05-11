package com.misstouch.springbootshirojwt.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author hechao
 * @date create in 9:38 2018/5/11/011
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReslutBean<T> implements Serializable {

    private int code;

    private String message;

    private T data;

}
