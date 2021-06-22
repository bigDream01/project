package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisConstant;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetmealService;
import com.itheima.entiy.PageResult;
import com.itheima.entiy.QueryPageBean;
import com.itheima.entiy.Result;
import com.itheima.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.UUID;

/*
 *
 *
 *
 *@description:
 *@author：bigDream
 *@date：2021-06-15 16:29
 **/
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    //使用redisPool操作redis
    @Autowired
    private JedisPool jedisPool;

    @Reference
    private SetmealService setmealService;

    /**
     * 将图片上传到云端，返回云端图片的云端地址
     * @param image
     * @return
     */
    @RequestMapping("/upload")
    public Result upload(@RequestParam("imgFile") MultipartFile image) {

        String originalFilename = image.getOriginalFilename();
        int lastIndexOf = originalFilename.lastIndexOf(".");
        String extention = originalFilename.substring(lastIndexOf - 1);//截取  .jpg   后缀

        String fileName = UUID.randomUUID().toString() + extention;

        try {
            //将文件上传到七牛云
            QiniuUtils.upload2Qiniu(image.getBytes(), fileName);
            //将图片的名称保存到redis
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }

        //前端需要fileName来进行页面显示
        return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, fileName);
    }

    /**
     *  添加套餐信息，将图片地址信息存储到数据库
     * @param setmeal
     * @param checkgroupIds
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestParam("checkgroupIds") Integer[] checkgroupIds, @RequestBody Setmeal setmeal) {


        try {
           setmealService.add(setmeal,checkgroupIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_SETMEAL_FAIL);
        }
        return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);
    }

    /**
     * 分页查询和特定查询
     *
     * @param queryPageBean
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {

        return setmealService.pageQuery(queryPageBean);

    }

}
