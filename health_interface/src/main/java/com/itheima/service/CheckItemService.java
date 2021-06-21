package com.itheima.service;


import com.itheima.entiy.PageResult;
import com.itheima.entiy.QueryPageBean;
import com.itheima.pojo.CheckItem;

import java.util.List;

/*
 *
 *
 *
 *@description:
 *@author：bigDream
 *@date：2021-06-10 19:20
 **/

public interface CheckItemService {
    public void add(CheckItem checkItem);
    public PageResult findPage(QueryPageBean queryPageBean);
    public void deleteById(Integer id);
    public CheckItem findById(Integer id);
    public void edit(CheckItem checkItem);
    public List<CheckItem> findAll();
}
