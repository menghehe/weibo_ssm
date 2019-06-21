package site.imcu.interceptor;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import site.imcu.po.ResponseResult;
import site.imcu.utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String url = request.getRequestURI();
        if (url.indexOf("/login")>0 || url.indexOf("/img")>0|| url.indexOf("register")>0){
            return true;
        }
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("Authorization");
        token = token.replace("\"","");
        System.out.println(token);
        if (token != null){
            boolean result = JwtUtil.verify(token);
            if(result){
                System.out.println("pass interceptor");
                return true;
            }
        }
        System.out.println("fail ");
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(-1);
        responseResult.setMsg("token认证失败");
        response.getWriter().write(JSON.toJSONString(responseResult));
        return false;
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
