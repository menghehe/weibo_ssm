package site.imcu.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import site.imcu.po.Comment;
import site.imcu.po.CommentVo;
import site.imcu.service.CommentService;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CommentServiceImplTest {

    @Autowired
    CommentService commentService;
    @Test
    public void insertComment() {
        CommentVo comment = new CommentVo();
        comment.setUserId(29);
        comment.setWeiboId(178);
        comment.setCommentTime(new Date());
        comment.setCommentContent("insert test22");
        commentService.insertComment(comment);
    }
    @Test
    public void queryComment(){
        System.out.println(commentService.queryComment(202));
    }

    @Test
    public void insertComment1() {
    }

    @Test
    public void queryComment1() {
    }
}