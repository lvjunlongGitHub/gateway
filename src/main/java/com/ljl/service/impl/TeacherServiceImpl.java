package com.ljl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljl.entity.Teacher;
import com.ljl.mapper.TeacherMapper;
import com.ljl.service.TeacherService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 测试表 服务实现类
 * </p>
 *
 * @author lvjunlong
 * @since 2019-08-21
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    /**
     * 保存
     *
     * @param teacher 老师信息
     * @return 保存成功返回true
     */
    @Override
    public Boolean saveTeacher(Teacher teacher) {
        return 1 == baseMapper.insert(teacher);
    }

    /**
     * 编辑
     *
     * @param teacher 老师信息
     * @return 编辑成功返回true
     */
    @Override
    public Boolean updateTeacher(Teacher teacher) {
        return 1 == baseMapper.updateById(teacher);
    }

    /**
     * 获取单个
     *
     * @param id 主键ID
     * @return 老师信息
     */
    @Override
    public Teacher getTeacher(Long id) {
        return baseMapper.selectById(id);
    }
}
