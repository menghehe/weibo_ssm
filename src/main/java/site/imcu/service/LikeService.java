package site.imcu.service;

import site.imcu.po.Like;

public interface LikeService {
    int insertLike(Like like);
    int deleteLike(Like like);
    int isLiked(Like like);
    int countLike(int weiboId);
}
