package site.imcu.mapper;

import org.apache.ibatis.annotations.Param;
import site.imcu.po.UserVo;

import java.util.List;

public interface UserVoMapper {
    List<UserVo> login(UserVo userVo);
    int register(UserVo userVo);
    int isUsernameExisted(String username);
    int queryWeiboCount(Integer userId);
    int queryFansCount(Integer userId);
    int queryFollowCount(Integer userId);
    UserVo queryUserById(int userId);
    List<UserVo> queryUserListByIdList(@Param("idList") List<Integer> idList);
    int updateUser(UserVo userVo);
}
