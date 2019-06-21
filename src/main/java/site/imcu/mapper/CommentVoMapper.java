package site.imcu.mapper;

import site.imcu.po.CommentVo;

import java.util.List;

public interface CommentVoMapper {
    int insertComment(CommentVo commentVo);
    List<CommentVo> queryComment(Integer weiboId);
    int countComment(int weiboId);
}
