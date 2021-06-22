package com.itheima.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.entiy.PageResult;
import com.itheima.entiy.QueryPageBean;
import com.itheima.pojo.CheckGroup;
import com.itheima.dao.CheckGroupDao;
import com.itheima.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *
 *
 *
 *@description:
 *@author：bigDream
 *@date：2021-06-13 11:40
 **/
@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    CheckGroupDao checkGroupDao;

    //    //setCheckGroupAndCheckItem
//    @Override
//    public void add(Integer[] checkitemIds, CheckGroup checkGroup) {
//        checkGroupDao.add(checkGroup);
//        Integer checkgroupId = checkGroup.getId();
//
//        if (checkitemIds != null && checkitemIds.length >0) {
//            for(Integer checkitemId : checkitemIds) {
//                Map<String, Integer> map = new HashMap<>();
//                map.put("checkitemId",checkitemId);
//                map.put("checkgroupId",checkgroupId);
//                checkGroupDao.setCheckItemIdAndCheckGroupId(map);
//            }
//        }
//    }
//新增检查组，同时需要让检查组关联检查项


    //建立检查组和检查项多对多关系
    @Override
    public void setCheckGroupAndCheckItem(Integer checkGroupId, Integer[] checkitemIds) {
        if (checkitemIds != null && checkitemIds.length > 0) {
            for (Integer checkitemId : checkitemIds) {
                Map<String, Integer> map = new HashMap<>();
                map.put("checkgroupId", checkGroupId);
                map.put("checkitemId", checkitemId);
                checkGroupDao.setCheckItemIdAndCheckGroupId(map);
            }
        }
    }

    /**
     * 分页查询
     *
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {

        //获取查询参数
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        //调用分页查询助手得到查询数据
        //currentPage ：开始查询的页数
        //pageSize：每页显示的条数
        PageHelper.startPage(currentPage, pageSize);

//        Page<CheckGroup> page = checkGroupDao.findByCondition(queryString);
        Page<CheckGroup> page = checkGroupDao.findByCondition(queryString);

        return new PageResult(page.getTotal(), page.getResult());
    }

    //检查组查询
    @Override
    public CheckGroup findById(Integer id) {
        return checkGroupDao.findById(id);
    }

    @Override
    public List<Integer> findCheckItemByCheckGroupId(Integer id) {

        List<Integer> checkItemIds = checkGroupDao.findCheckItemByCheckGroupId(id);

        return checkItemIds;
    }

    //修改检查组
    @Override
    public void edit(Integer[] checkitemIds, CheckGroup checkGroup) {
        Integer checkGroupId = checkGroup.getId();

        //先修改checkgroup检查组
        checkGroupDao.edit(checkGroup);
        //清除当前关联表的检查组的检查项
        checkGroupDao.deleteAssociation(checkGroupId);
        ///重新设置检查组和检查项的关联表
        setCheckGroupAndCheckItem(checkGroupId, checkitemIds);
    }

    //删除检查组和关联表的信息
    @Override
    public void deleteGroup(Integer id) {
        checkGroupDao.deleteAssociation(id);
        checkGroupDao.deleteGroupById(id);
    }

    //查询所有的checkgroup
    @Override
    public List<CheckGroup> findAll() {
        List<CheckGroup> list = checkGroupDao.findAll();
        return list;
    }

    //增加检查组
    @Override
    public void add(Integer[] checkitemIds, CheckGroup checkGroup) {
        //新增检查组，操作t_checkgroup表
        checkGroupDao.add(checkGroup);
        //设置检查组和检查项的多对多的关联关系，操作t_checkgroup_checkitem表
        Integer checkGroupId = checkGroup.getId();
        this.setCheckGroupAndCheckItem(checkGroupId, checkitemIds);
    }


}
