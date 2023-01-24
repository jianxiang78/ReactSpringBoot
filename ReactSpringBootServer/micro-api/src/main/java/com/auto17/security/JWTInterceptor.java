package com.auto17.security;

import com.auto17.domain.AjaxResult;
import com.auto17.domain.AppUser;
import com.auto17.service.IAppUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author
 */

public class JWTInterceptor extends HandlerInterceptorAdapter {

    protected final Logger log = LoggerFactory.getLogger(JWTInterceptor.class);

    @Autowired
    private IAppUserService appUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("authorization");
        log.info("token="+token);
        if(StringUtils.isEmpty(token)){
            log.error("authorization is Empty");
            errorOut(response,"authorization is Empty");
            return false;
        }
        try {
            //get login user info
            String userNoString=JWTUtils.getAudience(token);
            log.info("userNoString="+userNoString);
            AppUser loginUser=appUserService.selectAppUserById(Long.valueOf(userNoString));
            if(loginUser!=null){
                JWTUtils.verify(token,loginUser.getUserTrueName());
                request.setAttribute("loginUser", loginUser);
            }else{
                errorOut(response,"check user fail");
            }
        } catch (Exception e) {
            errorOut(response,"check verify fail");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void errorOut(HttpServletResponse response,String msg){
        try {
            response.setHeader("Access-Control-Allow-Origin","*");
            response.setContentType("text/json; charset=utf-8");
            response.setHeader("Access-Control-Allow-Methods","GET,POST,OPTIONS,DELETE");
            response.setHeader("Access-Control-Max-Age","3600");
            response.addHeader("Access-Control-Allow-Headers", "*");
            PrintWriter writer=response.getWriter();
            writer.print(AjaxResult.error(msg));
            writer.flush();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
