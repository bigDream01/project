package com.itheima.service;

/*
 *
 *
 *
 *@description:
 *@author：bigDream
 *@date：2021-06-22 11:30
 **/


import com.itheima.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

public interface OrderSettingService {

    public void add(List<OrderSetting> orderSettings);

    public List<Map> getOrderSettingByMonth(String date);

    public void editNumberByDate(OrderSetting orderSetting);

}
