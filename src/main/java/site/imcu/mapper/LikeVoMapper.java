package site.imcu.mapper;

import site.imcu.po.Like;

public interface LikeVoMapper {
    int insertLike(Like like);
    int deleteLike(Like like);
    int isLiked(Like like);
    int countLike(int weiboId);
}
