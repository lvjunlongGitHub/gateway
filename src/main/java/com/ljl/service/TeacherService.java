package com.ljl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljl.entity.Teacher;

/**
 * <p>
 * 测试表 服务类
 * </p>
 *
 * @author lvjunlong
 * @since 2019-08-21
 */
public interface TeacherService extends IService<Teacher> {

    /**
     * 保存
     * @param teacher 老师信息
     * @return 保存成功返回true
     */
    Boolean saveTeacher(Teacher teacher);

    /**
     * 编辑
     * @param teacher 老师信息
     * @return 编辑成功返回true
     */
    Boolean updateTeacher(Teacher teacher);

    /**
     * 获取单个
     * @param id 主键ID
     * @return 老师信息
     */
    Teacher getTeacher(Long id);

}
