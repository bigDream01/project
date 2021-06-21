package com.itheima.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.CheckItemDao;

import com.itheima.entiy.PageResult;
import com.itheima.entiy.QueryPageBean;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 *
 *
 *
 *@description:
 *@author：bigDream
 *@date：2021-06-10 19:31
 **/

/**
 * 检查项服务
 */

//明确实现的是那个服务接口
@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {


    @Autowired
    private CheckItemDao checkItemDao;

    /**
     * 增加检查项
     *
     * @param checkItem
     */
    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    /**
     * 分页查询
     *
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        //完成分页查询，基于mybatis分页 插件 查询

        //基于threadlocal本地线程来实现对数据的查询，自动进行对对象的封装和数据查询
        ///根据拦截器来实现的，在SQL语句实现的后面加上limit关键字
        PageHelper.startPage(currentPage, pageSize);

        //可以对得到数据进行从新的统计
        Page<CheckItem> page = checkItemDao.selectByCondition(queryString);

        long total = page.getTotal();
        List<CheckItem> rows = page.getResult();
        PageResult pageResult = new PageResult(total, rows);

        return pageResult;
    }

    /**
     * 删除检查项
     *
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        //检查是否关联到检查组
        long count = checkItemDao.getCountById(id);

        if (count > 0) {
            //不允许删除
            new RuntimeException();
        }
        checkItemDao.deleteCheckItemById(id);
    }

    @Override
    public CheckItem findById(Integer id) {
        CheckItem checkItem = checkItemDao.findById(id);

        return checkItem;
    }

    @Override
    public void edit(CheckItem checkItem) {
        checkItemDao.edit(checkItem);
    }

    @Override
    public List<CheckItem> findAll() {
        List<CheckItem> list = checkItemDao.findAll();
        return list;
    }

}
