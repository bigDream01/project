package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.OrderSettingDao;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/*
 *
 *
 *
 *@description: 预约设置服务
 *@author：bigDream
 *@date：2021-06-22 11:46
 **/

@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    OrderSettingDao orderSettingDao;

    //添加预约数据
    @Override
    public void add(List<OrderSetting> orderSettings) {
        if (orderSettings != null && orderSettings.size() > 0) {

            for (OrderSetting orderSetting : orderSettings) {
                //判断当前设置是否已经进行了设置
                long countByOrderDate = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
                if (countByOrderDate > 0) {

                    orderSettingDao.editNumberByOrderDate(orderSetting);
                } else {
                    orderSettingDao.add(orderSetting);
                }
            }

        }
    }

    /**
     * 根据月份查询预约数据
     *
     * @param date
     * @return
     */
    @Override
    public List<Map> getOrderSettingByMonth(String date) {


        String begin = date + "-1";
        String end = date + "-31";

        Map<String, String> map = new HashMap<>();

        map.put("begin", begin);
        map.put("end", end);

        //页面需要的数据
        List<Map> res = new ArrayList<>();

        //得到OrderSetting数据，需要转化为页面需要的数据
        List<OrderSetting> list = orderSettingDao.getOrderSettingByMonth(map);

        //转化数据
        if (list != null && list.size() > 0) {
            for (OrderSetting orderSetting : list) {

                Map<String, Object> temp = new HashMap<>();
                temp.put("date", orderSetting.getOrderDate().getDate());
                temp.put("number", orderSetting.getNumber());
                temp.put("reservations", orderSetting.getReservations());
                res.add(temp);
            }

        }

        return res;
    }


}
