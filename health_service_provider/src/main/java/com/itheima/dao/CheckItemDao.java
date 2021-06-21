package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckItem;

import java.util.List;


/*
 *
 *
 *
 *@description:
 *@author：bigDream
 *@date：2021-06-10 19:59
 **/
public interface CheckItemDao {
    public void add(CheckItem checkItem);
    public Page<CheckItem> selectByCondition(String queryString);
    public long getCountById(Integer id);
    public void deleteCheckItemById(Integer id);
    public CheckItem findById(Integer id);
    public void edit(CheckItem checkItem);
    public List<CheckItem> findAll();
}
