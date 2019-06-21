package site.imcu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.imcu.mapper.UserVoMapper;
import site.imcu.po.User;
import site.imcu.po.UserVo;
import site.imcu.service.UserService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserVoMapper userVoMapper;
    @Override
    public List<UserVo> login(UserVo userVo) {
        return userVoMapper.login(userVo);
    }

    @Override
    public int register(UserVo userVo) {
        return userVoMapper.register(userVo);
    }

    @Override
    public int isUsernameExisted(String username) {
        return userVoMapper.isUsernameExisted(username);
    }

    @Override
    public int calculateAge(Date bir) {
        Calendar cal_bir = Calendar.getInstance();
        Calendar cal_now = Calendar.getInstance();

        cal_bir.setTime(bir);
        cal_now.setTime(new Date());

        int age = cal_now.get(Calendar.YEAR) - cal_bir.get(Calendar.YEAR);
        return age;
    }

    @Override
    public int queryWeiboCount(Integer userId) {
        return userVoMapper.queryWeiboCount(userId);
    }

    @Override
    public int queryFollowCount(Integer userId) {
        return userVoMapper.queryFollowCount(userId);
    }

    @Override
    public int queryFansCount(Integer userId) {
        return userVoMapper.queryFansCount(userId);
    }

    @Override
    public UserVo queryUserById(int userId) {
        return userVoMapper.queryUserById(userId);
    }

    @Override
    public List<UserVo> queryUserListByIdList(List<Integer> idList) {
        return userVoMapper.queryUserListByIdList(idList);
    }

    @Override
    public int updateUser(UserVo userVo) {
        return userVoMapper.updateUser(userVo);
    }
}
