package site.imcu.service;


import site.imcu.po.User;
import site.imcu.po.UserVo;

import java.util.Date;
import java.util.List;

public interface UserService {
    int register(UserVo userVo);
    int isUsernameExisted(String username);
    List<UserVo> login(UserVo userVo);
    int calculateAge(Date bir);
    int queryWeiboCount(Integer userId);
    int queryFollowCount(Integer userId);
    int queryFansCount(Integer userId);
    UserVo queryUserById(int userId);
    List<UserVo> queryUserListByIdList(List<Integer> idList);
    int updateUser(UserVo userVo);

}
