package site.imcu.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import site.imcu.po.Relation;
import site.imcu.service.RelationService;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class RelationServiceImplTest {
    @Autowired
    RelationService relationService;

    @Test
    public void queryRelation() {
        Relation relation = new Relation();
        relation.setUserId(29);
        relation.setFollowId(40);
        System.out.println(relationService.queryRelation(relation).toString());
    }

    @Test
    public void countRelation(){
        Relation relation = new Relation();
        relation.setUserId(29);
        relation.setFollowId(30);
        relation.setState(1);
        System.out.println(relation);
        System.out.println(relationService.insertRelation(relation));
    }
}