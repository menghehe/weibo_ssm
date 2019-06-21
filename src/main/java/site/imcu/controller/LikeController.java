package site.imcu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import site.imcu.po.Like;
import site.imcu.po.Relation;
import site.imcu.po.ResponseResult;
import site.imcu.service.LikeService;
import site.imcu.service.RelationService;
import site.imcu.utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class LikeController {

    @Autowired
    LikeService likeService;

    @RequestMapping(value = "/changeLike",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult changeLike(HttpServletRequest request, int weiboId){
        ResponseResult responseResult = new ResponseResult();
        String token = request.getHeader("Authorization");
        int userId = JwtUtil.getUserId(token);
        Like like = new Like();
        like.setUserId(userId);
        like.setWeiboId(weiboId);
        int result;
        if (likeService.isLiked(like)==0){
            like.setLikeTime(new Date());
            result = likeService.insertLike(like);
        }else {
            result = likeService.deleteLike(like);
        }
        if (result == 1){
            responseResult.setCode(1);
        }else {
            responseResult.setCode(0);
        }
        return responseResult;
    }
}
