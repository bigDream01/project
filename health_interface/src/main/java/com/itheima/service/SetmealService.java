package com.itheima.service;

import com.itheima.pojo.Setmeal;
import com.itheima.entiy.PageResult;
import com.itheima.entiy.QueryPageBean;

/*
 *
 *
 *
 *@description:
 *@author：bigDream
 *@date：2021-06-15 18:53
 **/
public interface SetmealService {

    public void add(Setmeal setmeal, Integer[] checkgroupIds);

    public PageResult pageQuery(QueryPageBean queryPageBean);

}
