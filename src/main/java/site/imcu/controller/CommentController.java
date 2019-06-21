package site.imcu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import site.imcu.po.CommentVo;
import site.imcu.po.ResponseResult;
import site.imcu.service.CommentService;
import site.imcu.utils.DateConvert;
import site.imcu.utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult postComment(HttpServletRequest request, CommentVo commentVo){
        ResponseResult responseResult = new ResponseResult();
        System.out.println(commentVo.toString());
        String token = request.getHeader("Authorization");
        commentVo.setCommentTime(new Date());
        commentVo.setUserId(JwtUtil.getUserId(token));
        int result = commentService.insertComment(commentVo);
        if (result == 1){
            responseResult.setCode(1);
            responseResult.setMsg("评论成功");
            return  responseResult;
        }
        responseResult.setCode(0);
        responseResult.setMsg("评论失败");
        return  responseResult;
    }

    @RequestMapping(value = "/comment",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult queryComment(Integer weiboId){
        ResponseResult<List<CommentVo>> responseResult = new ResponseResult<>();
        List<CommentVo> commentVoList = commentService.queryComment(weiboId);
        for (int i=0;i<commentVoList.size();i++){
            commentVoList.get(i).setTime(DateConvert.convert2s(commentVoList.get(i).getCommentTime()));
        }
        responseResult.setCode(1);
        responseResult.setMsg("查询评论成功");
        responseResult.setData(commentVoList);
        return responseResult;
    }


}
