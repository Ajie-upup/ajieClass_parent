package com.ajie.vd.controller;


import com.ajie.config.result.Result;
import com.ajie.model.vod.Teacher;
import com.ajie.vd.service.TeacherService;
import com.ajie.vo.vod.TeacherQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author ajie
 * @since 2022-10-11
 */
@Api(tags = "讲师管理系统")
@RestController
@RequestMapping(value = "admin/vd/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;


    //    @ApiOperation("查询所有讲师")
//    @GetMapping("/findAll")
//    public List<Teacher> findAllTeacher() {
//        List<Teacher> teacherList = teacherService.list();
//        return teacherList;
//    }
    @ApiOperation("查询所有讲师")
    @GetMapping("/findAll")
    public Result findAllTeacher() {
        List<Teacher> teacherList = teacherService.list();
        return Result.success(teacherList).message("查询数据成功");
    }

    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("/remove/{id}")
    public Result removeTeacher(@ApiParam(name = "id", value = "ID", required = true)
                                @PathVariable Long id) {
        boolean result = teacherService.removeById(id);
        if (result) {
            return Result.success(null);
        } else {
            return Result.fail(null);
        }
    }

    @ApiOperation(value = "条件查询分页")
    @PostMapping("{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "teacherVo", value = "查询对象", required = false)
            @RequestBody(required = false) TeacherQueryVo teacherQueryVo) {
        //创建page对象，传递当前页和每页记录数
        Page<Teacher> pageParam = new Page<>(page, limit);
        //获取条件值
        String name = teacherQueryVo.getName();//讲师名称
        Integer level = teacherQueryVo.getLevel();//讲师级别
        String joinDateBegin = teacherQueryVo.getJoinDateBegin();//开始时间
        String joinDateEnd = teacherQueryVo.getJoinDateEnd();//结束时间
        //封装条件
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(joinDateBegin)) {
            wrapper.ge("join_date", joinDateBegin);
        }
        if (!StringUtils.isEmpty(joinDateEnd)) {
            wrapper.le("join_date", joinDateEnd);
        }
        //调用方法得到分页查询结果
        IPage<Teacher> pageModel = teacherService.page(pageParam, wrapper);
        return Result.success(pageModel);
    }

    @ApiOperation("添加讲师")
    @PostMapping("/saveTeacher")
    public Result saveTeacher(@RequestBody Teacher teacher) {
        boolean isSuccess = teacherService.save(teacher);
        if (isSuccess) {
            return Result.success(null);
        } else {
            return Result.fail(null);
        }
    }

    @ApiOperation("根据id查询讲师")
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Long id) {
        Teacher teacher = teacherService.getById(id);
        if (teacher != null) {
            return Result.success(teacher);
        } else {
            return Result.fail(null);
        }
    }

    @ApiOperation("修改讲师信息")
    @PostMapping("/updateTeacher")
    public Result updateTeacher(@RequestBody Teacher teacher) {
        boolean isSuccess = teacherService.updateById(teacher);
        if (isSuccess) {
            return Result.success(null);
        } else {
            return Result.fail(null);
        }
    }

    @ApiOperation("批量删除讲师")
    @DeleteMapping("/removeBatch")
    public Result removeBatch(@RequestBody List<Long> idList){
        boolean isSuccess = teacherService.removeByIds(idList);
        if (isSuccess) {
            return Result.success(null);
        } else {
            return Result.fail(null);
        }
    }


}

