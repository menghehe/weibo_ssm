package site.imcu.service;

import site.imcu.po.Comment;
import site.imcu.po.CommentVo;

import java.util.List;

public interface CommentService {
    int insertComment(CommentVo commentVo);
    List<CommentVo> queryComment(Integer weiboId);
    int countComment(int weiboId);
}
