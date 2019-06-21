package site.imcu.controller;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import site.imcu.po.Like;
import site.imcu.po.ResponseResult;
import site.imcu.po.WeiboVo;
import site.imcu.service.CommentService;
import site.imcu.service.LikeService;
import site.imcu.service.WeiboService;
import site.imcu.utils.CountPic;
import site.imcu.utils.DateConvert;
import site.imcu.utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/weibo")
public class WeiboController {

    @Autowired
    WeiboService weiboService;
    @Autowired
    LikeService likeService;
    @Autowired
    CommentService commentService;

    //获取微博
    @RequestMapping(value = "/timeline",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult queryTimeLine(int pageNum,int pageSize,HttpServletRequest request) {
        ResponseResult<List<WeiboVo>> responseResult = new ResponseResult<>();
        String token = request.getHeader("Authorization");
        List<Integer> idList = weiboService.queryFollowId(JwtUtil.getUserId(token));
        idList.add(JwtUtil.getUserId(token));
        List<WeiboVo> weiboVoList = alterWeiboList(weiboService.queryTimeLine(idList,pageNum,pageSize),JwtUtil.getUserId(token));
        responseResult.setCode(1);
        responseResult.setMsg("查询时间线成功");
        responseResult.setData(weiboVoList);
        return responseResult;
    }

    //获取微博
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult queryAll(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        ResponseResult<List<WeiboVo>> responseResult = new ResponseResult<>();
        List<WeiboVo> weiboVoList = alterWeiboList(weiboService.queryAll(),JwtUtil.getUserId(token));
        responseResult.setCode(1);
        responseResult.setMsg("查询成功");
        responseResult.setData(weiboVoList);
        return responseResult;
    }



    //获取微博
    @RequestMapping(value = "/android",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult android(int mark,int afterOrBefore ,int pageNum,int pageSize,HttpServletRequest request) {
        ResponseResult<List<WeiboVo>> responseResult = new ResponseResult<>();
        String token = request.getHeader("Authorization");
        List<Integer> idList = weiboService.queryFollowId(JwtUtil.getUserId(token));
        idList.add(JwtUtil.getUserId(token));
        List<WeiboVo> weiboVoList = new ArrayList<>();
        if (mark == 1){
            weiboVoList= alterWeiboList(weiboService.afterTimeLine(idList,pageNum,pageSize,afterOrBefore),JwtUtil.getUserId(token));
        }
        if (mark == -1){
            weiboVoList = alterWeiboList(weiboService.beforeTimeLine(idList,pageNum,pageSize,afterOrBefore),JwtUtil.getUserId(token));
        }
        responseResult.setCode(1);
        responseResult.setMsg("查询时间线成功");
        responseResult.setData(weiboVoList);
        return responseResult;
    }

    @RequestMapping(value = "/someone",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult queryWeiboByUserId(int userId,int pageNum,int pageSize,HttpServletRequest request){
        String token = request.getHeader("Authorization");
        ResponseResult<List<WeiboVo>> responseResult = new ResponseResult<>();
        List<Integer> idList = new ArrayList<>();
        idList.add(userId);
        List<WeiboVo> weiboVoList = alterWeiboList(weiboService.queryTimeLine(idList,pageNum,pageSize),JwtUtil.getUserId(token));
        responseResult.setCode(1);
        responseResult.setMsg("查询成功");
        responseResult.setData(weiboVoList);
        return responseResult;
    }

    @RequestMapping(value = "/one",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult queryWeiboById(int weiboId,HttpServletRequest request){
        String token = request.getHeader("Authorization");
        ResponseResult<WeiboVo> responseResult = new ResponseResult<>();
        WeiboVo weiboVo = weiboService.queryById(weiboId);
        List<WeiboVo> weiboVoList = new ArrayList<>();
        weiboVoList.add(weiboVo);
        responseResult.setCode(1);
        responseResult.setMsg("查询成功");
        responseResult.setData(alterWeiboList(weiboVoList,JwtUtil.getUserId(token)).get(0));
        return responseResult;
    }

    private List<WeiboVo> alterWeiboList(List<WeiboVo> weiboVoList,int userId){
        for (int i=0;i<weiboVoList.size();i++) {
            weiboVoList.get(i).setDate(DateConvert.convert2s(weiboVoList.get(i).getPostTime()));
            weiboVoList.get(i).setImgCount(CountPic.getNumber(weiboVoList.get(i)));
            weiboVoList.get(i).setLikeCount(likeService.countLike(weiboVoList.get(i).getWeiboId()));
            weiboVoList.get(i).setCommentCount(commentService.countComment(weiboVoList.get(i).getWeiboId()));
            Like like = new Like();
            like.setWeiboId(weiboVoList.get(i).getWeiboId());
            like.setUserId(userId);
            weiboVoList.get(i).setLikes(likeService.isLiked(like));

        }
        return weiboVoList;
    }

    //发微博
    @RequestMapping(value = "/post",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult postWeibo(WeiboVo weiboVo, HttpServletRequest request){
        ResponseResult responseResult = new ResponseResult();
        String token = request.getHeader("Authorization");
        Date now = new Date();
        weiboVo.setPostTime(now);
        weiboVo.setUserId(JwtUtil.getUserId(token));
        int mark =  weiboService.postWeibo(weiboVo);
        if (mark == 1){
            responseResult.setCode(1);
            responseResult.setMsg("发微博成功");
            return  responseResult;
        }
        responseResult.setCode(0);
        responseResult.setMsg("发微博失败");
        return  responseResult;
    }

    // 图片上传
    @RequestMapping(value = "/img", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<String> upload(@RequestParam("file")MultipartFile file) throws Exception {

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

            String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));


            File newFile = new File(pic_path + newFileName);

            file.transferTo(newFile);


            responseResult.setCode(1);
            responseResult.setMsg("上传成功");
            responseResult.setData(newFileName);
            return responseResult;
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
