package com.ljl.util.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lvjunlong
 * @date 2019/8/22 上午9:28
 */
@Data
public class CommonResponse implements Serializable {

    private Long requestId;

    private Integer stateCode;

    private String code;

    private String message;

}
