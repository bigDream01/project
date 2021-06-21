package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;

import com.itheima.entiy.PageResult;
import com.itheima.entiy.QueryPageBean;
import com.itheima.entiy.Result;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*
 *
 *
 *
 *@description:
 *@author：bigDream
 *@date：2021-06-10 19:14
 **/
@RestController
@RequestMapping("/checkitem")
public class CheckItemController {

    @Reference//查找服务
    private CheckItemService checkItemService;


    @RequestMapping("/add")
    public Result add(@RequestBody CheckItem checkItem) {

        try {
            checkItemService.add(checkItem);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }

        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }


    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {

        PageResult pageResult = checkItemService.findPage(queryPageBean);

        return pageResult;
    }

    //删除检查项
    @RequestMapping("/deleteItem")
    public Result deleteItem(Integer id) {

        try {
            checkItemService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
        }

        return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }

    //查找回显数据
    @ResponseBody
    @RequestMapping("/findById")
    public Result findById(Integer id) {
        CheckItem checkItem = null;

        try {
            checkItem = checkItemService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }

        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkItem);
    }

    //删除检查项
    @RequestMapping("/edit")
    public Result edift(@RequestBody CheckItem checkItem) {

        try {
            checkItemService.edit(checkItem);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_CHECKITEM_FAIL);
        }

        return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }

    //查询所有的检查项
    @RequestMapping("/findAll")
    public Result findAll() {
        List <CheckItem> list;
        try {
            list = checkItemService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }

        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,list);
    }

}
