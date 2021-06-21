package com.itheima.service;


import com.itheima.entiy.PageResult;
import com.itheima.entiy.QueryPageBean;
import com.itheima.entiy.Result;
import com.itheima.pojo.CheckGroup;

import java.util.List;

/*
 *
 *
 *
 *@description:
 *@author：bigDream
 *@date：2021-06-13 11:35
 **/

public interface CheckGroupService {
    public void add(Integer[] checkitemIds, CheckGroup checkGroup);

    public void setCheckGroupAndCheckItem(Integer checkGroupId, Integer[] checkitemIds);

    public PageResult pageQuery(QueryPageBean queryPageBean);

    public CheckGroup findById(Integer id);

    public List<Integer> findCheckItemByCheckGroupId(Integer id);

    public void edit(Integer[] checkitemIds, CheckGroup checkGroup);

    public void deleteGroup(Integer id);

    public List<CheckGroup> findAll();


}
