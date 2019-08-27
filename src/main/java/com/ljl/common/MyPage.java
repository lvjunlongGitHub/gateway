package com.ljl.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lvjunlong
 * @date 2019/8/27 下午2:39
 */
@Data
@AllArgsConstructor
public class MyPage<T> {

    private Long total;
    private List<T> list;

    public MyPage() {
        this.total = 0L;
        list = new ArrayList<>();
    }
}
