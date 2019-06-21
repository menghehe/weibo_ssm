package site.imcu.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import site.imcu.po.Relation;
import site.imcu.po.UserVo;
import site.imcu.service.RelationService;
import site.imcu.service.UserService;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserServiceImplTest {
    @Autowired
    UserService userService;
    RelationService relationService;

    @Test
    public void loginCheck() {

    }

    @Test
    public void registerUser() {
    }

    @Test
    public void findByUsername() {
    }

    @Test
    public void login() {
        UserVo userVo = new UserVo();
        userVo.setUsername("menghe");
        userVo.setPassword("123456");
        UserVo result =  userService.login(userVo).get(0);
        System.out.println(result.toString());
    }

    @Test
    public void calculateAge() {
    }

    @Test
    public void queryWeiboCount() {
    }

    @Test
    public void queryFollowCount() {
    }

    @Test
    public void queryFansCount() {

    }

    @Test
    public void queryRelation() {
        Relation relation = new Relation();
        relation.setUserId(29);
        relation.setState(1);
        relation.setFollowId(40);
    }

    @Test
    public void loginCheck1() {
    }

    @Test
    public void registerUser1() {
    }

    @Test
    public void findByUsername1() {
    }

    @Test
    public void login1() {
    }

    @Test
    public void calculateAge1() {
    }

    @Test
    public void queryWeiboCount1() {
    }

    @Test
    public void queryFollowCount1() {
    }

    @Test
    public void queryFansCount1() {
    }

    @Test
    public void queryUserById() {
        UserVo userVo = userService.queryUserById(999);
        System.out.println(userVo);
    }

    @Test
    public void register() {
        UserVo userVo = new UserVo();
        userVo.setUsername("ajax");
        userVo.setPassword("123456");
        userVo.setBir(new Date());
        userVo.setFace("default.png");
        userVo.setSex(1);
        userService.register(userVo);
    }

    @Test
    public void isUsernameExisted() {
        System.out.println(userService.isUsernameExisted("menghe"));
    }

    @Test
    public void updateUser() {
        UserVo userVo = new UserVo();
        userVo.setUserId(29);
        userVo.setFace("default.png");
        int result = userService.updateUser(userVo);
        System.out.println(result);

    }
}