package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Setmeal;

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
public interface SetmealDao {
    public void add(Setmeal setmeal);
    public void setSetmealAndCheckGroup(Map<String,Integer> map);

    public Page<Setmeal> findByCondition(String queryString);

}
