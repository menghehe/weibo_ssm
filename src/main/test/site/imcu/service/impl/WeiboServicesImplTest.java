package site.imcu.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import site.imcu.po.WeiboVo;
import site.imcu.service.WeiboService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class WeiboServicesImplTest {

    @Autowired
    WeiboService weiboService;

    @Test
    public void postWeibo() {
    }

    @Test
    public void queryFollowId() {
    }

    @Test
    public void queryTimeLine() {
        List<Integer> list = new ArrayList<>();
        list.add(29);
        List<WeiboVo> weiboVoList = weiboService.queryTimeLine(list,1,5);
        for (WeiboVo weiboVo:weiboVoList
             ) {
            System.out.println(weiboVo.toString());
        }
    }

    @Test
    public void beforeTimeLine() {
        List<Integer> list = new ArrayList<>();
        list.add(29);
        List<WeiboVo> weiboVoList = weiboService.beforeTimeLine(list,1,0,180);
        for (int i=0;i<weiboVoList.size();i++){
            System.out.println(weiboVoList.get(i).getWeiboId());
        }
    }

    @Test
    public void afterTimeLine() {
        List<Integer> list = new ArrayList<>();
        list.add(29);
        List<WeiboVo> weiboVoList = weiboService.afterTimeLine(list,1,0,180);
        for (int i=0;i<weiboVoList.size();i++){
            System.out.println(weiboVoList.get(i).getWeiboId());
        }
    }

    @Test
    public void queryAll() {
        System.out.println(weiboService.queryAll().size());
    }

    @Test
    public void queryById() {
        WeiboVo weiboVo =  weiboService.queryById(178);
        System.out.println(weiboVo.toString());
    }
}