package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.OrderSettingService;
import com.itheima.entiy.Result;
import com.itheima.utils.POIUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.PathParam;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/*
 *
 *
 *
 *@description:
 *@author：bigDream
 *@date：2021-06-22 11:10
 **/
@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {

    @Reference
    OrderSettingService orderSettingService;

    @RequestMapping("/upload")
    public Result upload(@RequestParam("excelFile") MultipartFile multipartFile) {

        List<String[]> list;
        try {
            list = POIUtils.readExcel(multipartFile);

            List<OrderSetting> orderSettings = new ArrayList<>();

            for (String[] item : list) {
                String date = item[0];
                String number = item[1];
                Date date1 = new Date(date);
                int num = Integer.parseInt(number);
                orderSettings.add(new OrderSetting(date1,num));
            }

            orderSettingService.add(orderSettings);

        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
        return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
    }

    /**
     * 根据月份查询数据
     * @param date
     * @return
     */
    @RequestMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String date) {//date格式 yy-MM

        List<Map> list = null;
        try {
            list = orderSettingService.getOrderSettingByMonth(date);
        } catch (Exception e) {
            e.printStackTrace();

            return new Result(false,MessageConstant.GET_ORDERSETTING_FAIL);
        }

        return new Result(true,MessageConstant.GET_ORDERSETTING_SUCCESS,list);
    }

    @RequestMapping("/editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting) {//date格式 yy-MM

        try {
            orderSettingService.editNumberByDate(orderSetting);
        } catch (Exception e) {
            e.printStackTrace();
           return  new Result(false,MessageConstant.ORDERSETTING_FAIL);
        }

        return new Result(true,MessageConstant.ORDERSETTING_SUCCESS);
    }




}
