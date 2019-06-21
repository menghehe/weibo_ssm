package site.imcu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import site.imcu.po.Relation;
import site.imcu.po.ResponseResult;
import site.imcu.service.RelationService;
import site.imcu.utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RelationController {
    @Autowired
    RelationService relationService;

    @RequestMapping(value = "changeRelation.action",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult changeRelation(HttpServletRequest request, int profileId){
        ResponseResult responseResult = new ResponseResult();
        String token = request.getHeader("Authorization");
        int userId = JwtUtil.getUserId(token);
        Relation relation = new Relation();
        relation.setUserId(userId);
        relation.setFollowId(profileId);
        int result;
        if (relationService.isInRelation(relation)==0){
            relation.setState(1);
            result = relationService.insertRelation(relation);
        }else {
            result = relationService.deleteRelation(relation);
        }
        if (result ==1){
            responseResult.setCode(1);
        }else {
            responseResult.setCode(0);
        }
        return responseResult;
    }
}
