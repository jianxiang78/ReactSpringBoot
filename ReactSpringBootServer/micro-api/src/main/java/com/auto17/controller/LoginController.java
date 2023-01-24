package com.auto17.controller;

import com.alibaba.fastjson.JSONObject;
import com.auto17.domain.AjaxResult;
import com.auto17.domain.AppUser;
import com.auto17.security.JWTUtils;
import com.auto17.service.IAppUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Login
 *
 * @author
 */

@RestController
@RequestMapping("/user")
@CrossOrigin
public class LoginController
{
    protected final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private IAppUserService appUserService;

    @PostMapping("/login")
    public AjaxResult login(AppUser user) {
        if(user.getUserLoginName()==null || user.getUserPasswd()==null){
            return AjaxResult.error("login fail");
        }
        user.setUserPasswd(DigestUtils.md5DigestAsHex(user.getUserPasswd().getBytes()));
        user=appUserService.selectAppUserOne(user);
        if(user==null){
            logger.error("login fail.Login name or password error.");
            return AjaxResult.error("login fail.Login name or password error.");
        }
        JSONObject object=new JSONObject();
        object.put("Authorization",JWTUtils.getToken(user));
        object.put("userRole",user.getUserRole());
        object.put("userTrueName",user.getUserTrueName());
        return AjaxResult.success(object);
    }


}
