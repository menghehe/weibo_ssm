package site.imcu.controller;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import site.imcu.po.Relation;
import site.imcu.po.ResponseResult;
import site.imcu.po.UserVo;
import site.imcu.service.RelationService;
import site.imcu.service.UserService;
import site.imcu.utils.DateConvert;
import site.imcu.utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    RelationService relationService;
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult login(String username, String password){
        ResponseResult<UserVo> responseResult = new ResponseResult<>();
        UserVo userVo = new UserVo();
        userVo.setUsername(username);
        userVo.setPassword(password);
        List<UserVo> userVoList = userService.login(userVo);
        if (userVoList.size()==1){
            UserVo result = userVoList.get(0);
            String token = JwtUtil.sign(result.getUserId(),result.getUsername(),result.getPassword());
            result.setAge(userService.calculateAge(result.getBir()));
            result.setBir_String(result.getBir().toString());
            int weiboCount = userService.queryWeiboCount(result.getUserId());
            int followCount = userService.queryFollowCount(result.getUserId());
            int fansCount = userService.queryFansCount(result.getUserId());
            result.setWeiboCount(weiboCount);
            result.setFollowCount(followCount);
            result.setFansCount(fansCount);
            responseResult.setCode(1);
            responseResult.setMsg("登陆成功");
            responseResult.setToken(token);
            responseResult.setData(result);
            return responseResult;
        }
        responseResult.setCode(0);
        responseResult.setMsg("登录失败");
        return responseResult;
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult register(String newUsername,String newPassword){
        ResponseResult responseResult = new ResponseResult();
        int result = userService.isUsernameExisted(newUsername);
        if (result == 1){
            responseResult.setCode(0);
            responseResult.setMsg("用户名存在换一个吧");
            return responseResult;
        }
        UserVo user = new UserVo();
        user.setUsername(newUsername);
        user.setPassword(newPassword);
        user.setNickname(newUsername);
        user.setFace("default.png");
        Date date = new Date();
        user.setBir(date);
        user.setSex(1);
        result = userService.register(user);
        if (result == 1){
            responseResult.setCode(1);
            responseResult.setMsg("注册成功");
            return responseResult;
        }
        responseResult.setCode(0);
        responseResult.setMsg("我也不知道发生了什么");
        return responseResult;
    }

    @RequestMapping(value = "/profile",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult queryUserPage(int profileId, HttpServletRequest request) throws SqlSessionException {
        ResponseResult<UserVo> responseResult = new ResponseResult<>();
        String token = request.getHeader("Authorization");
        Relation relation = new Relation();
        System.out.println(relation.toString());
        relation.setUserId(JwtUtil.getUserId(token));
        relation.setFollowId(profileId);
        UserVo userVo = userService.queryUserById(profileId);
        if (userVo == null){
            responseResult.setCode(0);
            responseResult.setMsg("用户不存在");
            return responseResult;
        }
        userVo.setFansCount(userService.queryFansCount(profileId));
        userVo.setWeiboCount(userService.queryWeiboCount(profileId));
        userVo.setFollowCount(userService.queryFollowCount(profileId));
        userVo.setBir_String(DateConvert.convert2d(userVo.getBir()));
        try {
            userVo.setRelation(relationService.queryRelation(relation));
        }catch (Exception e){
            userVo.setRelation(relation);
            e.printStackTrace();
        }
        responseResult.setCode(1);
        responseResult.setMsg("查询用户成功");
        responseResult.setData(userVo);
        return responseResult;
    }

    @RequestMapping(value = "/fresh",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult freshInfo(HttpServletRequest request){
        ResponseResult<UserVo> responseResult = new ResponseResult<>();
        String token = request.getHeader("Authorization");
        UserVo userVo = userService.queryUserById(JwtUtil.getUserId(token));
        userVo.setAge(userService.calculateAge(userVo.getBir()));
        userVo.setBir_String(userVo.getBir().toString());
        userVo.setWeiboCount(userService.queryWeiboCount(userVo.getUserId()));
        userVo.setFollowCount(userService.queryFollowCount(userVo.getUserId()));
        userVo.setFansCount(userService.queryFansCount(userVo.getUserId()));
        responseResult.setCode(1);
        responseResult.setMsg("更新成功");
        responseResult.setData(userVo);
        responseResult.setToken(JwtUtil.sign(userVo.getUserId(),userVo.getUsername(),userVo.getPassword()));
        return responseResult;
    }

    @RequestMapping(value = "/fans",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult queryAllFan(HttpServletRequest request){
        ResponseResult<List<UserVo>> responseResult = new ResponseResult<>();
        String token = request.getHeader("Authorization");
        List<UserVo> userVoList = userService.queryUserListByIdList(relationService.queryFanIds(JwtUtil.getUserId(token)));
        responseResult.setCode(1);
        responseResult.setData(userVoList);
        return responseResult;
    }

    @RequestMapping(value = "/follows",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult queryAllFollows(HttpServletRequest request){
        ResponseResult<List<UserVo>> responseResult = new ResponseResult<>();
        String token = request.getHeader("Authorization");
        List<UserVo> userVoList = userService.queryUserListByIdList(relationService.queryFollowIds(JwtUtil.getUserId(token)));
        responseResult.setCode(1);
        responseResult.setMsg("查询成功");
        responseResult.setData(userVoList);
        return responseResult;
    }

    @RequestMapping(value = "/changeAvatar",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<String> changeAvatar(HttpServletRequest request,MultipartFile file) throws Exception{
        String token = request.getHeader("Authorization");
        UserVo userVo = new UserVo();
        userVo.setUserId(JwtUtil.getUserId(token));
        ResponseResult<String> responseResult = new ResponseResult<>();
        // 原始名称
        String originalFilename = file.getOriginalFilename();
        // 上传图片
        if (file != null && originalFilename != null && originalFilename.length() > 0) {

            // 存储图片的物理路径
            String pic_path = "e:\\imgUpload\\";
            if(!isChartPathExist(pic_path)){
                pic_path = "/home/imgUpload/";
            }

            File path = new File(pic_path);
            if(!path.exists()){
                path.mkdirs();
            }

            // 新的图片名称
            String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));


            // 新图片
            File newFile = new File(pic_path + newFileName);

            // 将内存中的数据写入磁盘
            file.transferTo(newFile);

            // 将新图片名称写到user中

            userVo.setFace(newFileName);

            int result = userService.updateUser(userVo);
            if (result==1){
                responseResult.setCode(1);
                responseResult.setMsg("更新成功");
                responseResult.setData(newFileName);
                return responseResult;
            }
        }
        responseResult.setCode(0);
        return responseResult;
    }

    private static boolean isChartPathExist(String dirPath) {
        File file = new File(dirPath);
        if (!file.exists()) {
            return false;
        }
        return true;
    }

}