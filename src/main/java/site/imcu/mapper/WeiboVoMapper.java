package site.imcu.mapper;

import org.apache.ibatis.annotations.Param;
import site.imcu.po.WeiboVo;

import java.util.List;

public interface WeiboVoMapper {
    int postWeibo(WeiboVo weiboVo);
    List<WeiboVo> queryTimeLine(@Param("list") List<Integer> idList, @Param("pageNum") int pageNum,@Param("pageSize") int pageSize);
    List<Integer> queryFollowIds(int userId);
    List<WeiboVo> beforeTimeLine(@Param("list") List<Integer> idList, @Param("pageNum") int pageNum,@Param("pageSize") int pageSize,@Param("before")int before);
    List<WeiboVo> afterTimeLine(@Param("list") List<Integer> idList, @Param("pageNum") int pageNum,@Param("pageSize") int pageSize,@Param("after")int after);
    List<WeiboVo> queryAll();
    WeiboVo queryById(int weiboId);
}
