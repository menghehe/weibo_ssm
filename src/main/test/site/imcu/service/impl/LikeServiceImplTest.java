package site.imcu.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import site.imcu.mapper.LikeVoMapper;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class LikeServiceImplTest {

    @Autowired
    LikeVoMapper likeVoMapper;

    @Test
    public void insertLike() {
    }

    @Test
    public void deleteLike() {
    }

    @Test
    public void isLiked() {
    }

    @Test
    public void testAll(){

    }

    @Test
    public void countLike() {
    }
}