package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.entiy.PageResult;
import com.itheima.pojo.CheckGroup;

import java.util.List;
import java.util.Map;

/*
 *
 *
 *
 *@description:
 *@author：bigDream
 *@date：2021-06-13 11:47
 **/
public interface CheckGroupDao {
    public void add(CheckGroup checkGroup);

    public void setCheckItemIdAndCheckGroupId(Map<String, Integer> map);

    public Page<CheckGroup> findByCondition(String queryString);

    public CheckGroup findById(Integer id);


    public List<Integer> findCheckItemByCheckGroupId(Integer id);

    public void edit(CheckGroup checkGroup);

    public void deleteAssociation(Integer checkGroupId);


    public void deleteGroupById(Integer id);

    public List<CheckGroup> findAll();
}
