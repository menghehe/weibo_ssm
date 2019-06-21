package site.imcu.mapper;

import site.imcu.po.Relation;

import java.util.List;

public interface RelationMapper {
    Relation queryRelation(Relation relation);
    int insertRelation(Relation relation);
    int deleteRelation(Relation relation);
    int isInRelation(Relation relation);
    List<Integer> queryFollowIds(int userId);
    List<Integer> queryFanIds(int userId);

}
