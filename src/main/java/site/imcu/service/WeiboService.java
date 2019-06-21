package site.imcu.service;

import site.imcu.po.Weibo;
import site.imcu.po.WeiboVo;

import java.util.List;

public interface WeiboService {
    int postWeibo(WeiboVo weiboVo);
    List<Integer> queryFollowId(int id);
    List<WeiboVo> queryTimeLine(List<Integer> idList,int pageNum,int pageSize);
    List<WeiboVo> beforeTimeLine(List<Integer> idList,int pageNum,int pageSize,int before);
    List<WeiboVo> afterTimeLine(List<Integer> idList,int pageNum,int pageSize,int after);
    List<WeiboVo> queryAll();
    WeiboVo queryById(int weiboId);


}
