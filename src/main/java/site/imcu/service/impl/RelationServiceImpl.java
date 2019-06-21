package site.imcu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.imcu.mapper.RelationMapper;
import site.imcu.po.Relation;
import site.imcu.service.RelationService;

import java.util.List;

@Service
public class RelationServiceImpl implements RelationService {
    @Autowired
    RelationMapper relationMapper;
    @Override
    public Relation queryRelation(Relation relation) {
        System.out.println(relationMapper.queryRelation(relation).toString());
        return relationMapper.queryRelation(relation);
    }

    @Override
    public int insertRelation(Relation relation) {
        return relationMapper.insertRelation(relation);
    }

    @Override
    public int deleteRelation(Relation relation) {
        return relationMapper.deleteRelation(relation);
    }

    @Override
    public int isInRelation(Relation relation) {
        return relationMapper.isInRelation(relation);
    }

    @Override
    public List<Integer> queryFollowIds(int userId) {
        return relationMapper.queryFollowIds(userId);
    }

    @Override
    public List<Integer> queryFanIds(int userId) {
        return relationMapper.queryFanIds(userId);
    }
}
