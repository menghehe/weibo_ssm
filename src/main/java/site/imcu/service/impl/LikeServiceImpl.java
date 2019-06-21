package site.imcu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.imcu.mapper.LikeVoMapper;
import site.imcu.po.Like;
import site.imcu.service.LikeService;

@Service
public class LikeServiceImpl implements LikeService{

    @Autowired
    LikeVoMapper likeVoMapper;
    @Override
    public int insertLike(Like like) {
        return likeVoMapper.insertLike(like);
    }

    @Override
    public int deleteLike(Like like) {
        return likeVoMapper.deleteLike(like);
    }

    @Override
    public int isLiked(Like like) {
        return likeVoMapper.isLiked(like);
    }

    @Override
    public int countLike(int weiboId) {
        return likeVoMapper.countLike(weiboId);
    }
}
