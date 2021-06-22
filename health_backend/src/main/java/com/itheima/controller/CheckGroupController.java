package com.itheima.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entiy.PageResult;
import com.itheima.entiy.QueryPageBean;
import com.itheima.entiy.Result;
import com.itheima.pojo.CheckGroup;
import com.itheima.service.CheckGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 *
 *
 *
 *@description:
 *@author：bigDream
 *@date：2021-06-13 11:29
 **/

/**
 * 检查组管理
 */
@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {

    @Reference
    private CheckGroupService checkGroupService;

    /**
     * 添加检查组
     *
     * @param checkitemIds
     * @param checkGroup
     * @return
     */
    @RequestMapping("/add")
    public Result add(Integer[] checkitemIds, @RequestBody CheckGroup checkGroup) {

        try {
            checkGroupService.add(checkitemIds, checkGroup);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);

    }

    /**
     * 分页查询和特定查询
     *
     * @param queryPageBean
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {

        return checkGroupService.pageQuery(queryPageBean);

    }

    /**
     * 查询检查组
     *
     * @param id
     * @return
     */

    @RequestMapping("/findById")
    public Result findById(Integer id) {

        CheckGroup checkGroup;
        try {
            checkGroup = checkGroupService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, checkGroup);

    }

    @RequestMapping("/findCheckItemByCheckGroupId")
    public Result findCheckItemByCheckGroupId(Integer id) {
        List<Integer> CheckItemIds = null;

        try {
            CheckItemIds = checkGroupService.findCheckItemByCheckGroupId(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, CheckItemIds);

    }

    /**
     * 编辑方法
     *
     * @param checkitemIds
     * @param checkGroup
     * @return
     */
    @RequestMapping("/edit")
    public Result edit(Integer[] checkitemIds, @RequestBody CheckGroup checkGroup) {

        try {
            checkGroupService.edit(checkitemIds, checkGroup);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);

    }

    /**
     * 删除检查组
     * @param id
     * @return
     */
    @RequestMapping("/deleteGroup")
    public Result delete(Integer id) {


        try {
            checkGroupService.deleteGroup(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS);

    }

    /**
     *  查询所有检查组
     * @return
     */
    @RequestMapping("/findAll")
    public Result findAll(){

        List<CheckGroup> list;

        try {
          list = checkGroupService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,list);
    }

}
