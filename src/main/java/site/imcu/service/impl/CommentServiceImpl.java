package site.imcu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.imcu.mapper.CommentVoMapper;
import site.imcu.po.Comment;
import site.imcu.po.CommentVo;
import site.imcu.service.CommentService;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentVoMapper commentVoMapper;
    @Override
    public int insertComment(CommentVo commentVo) {
        return commentVoMapper.insertComment(commentVo);
    }

    @Override
    public List<CommentVo> queryComment(Integer weiboId) {
        return commentVoMapper.queryComment(weiboId);
    }

    @Override
    public int countComment(int weiboId) {
        return commentVoMapper.countComment(weiboId);
    }
}
