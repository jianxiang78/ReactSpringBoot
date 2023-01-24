package com.auto17.controller;

import com.auto17.domain.AjaxResult;
import com.auto17.domain.AppUser;
import com.auto17.page.TableDataInfo;
import com.auto17.service.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * app User Controller
 *
 * @author jianxiang sun
 * @date 2022-12-02
 */
@Controller
@RequestMapping("/appUser")
public class AppUserController extends BaseController
{

    @Autowired
    private IAppUserService appUserService;

    /**
     * Query app User list
     */
    @PostMapping("/list")
    @ResponseBody
    @CrossOrigin
    public TableDataInfo list(AppUser AppUser)
    {
        startPage();
        List<AppUser> list = appUserService.selectAppUserList(AppUser);
        return getDataTable(list);
    }

    /**
     * new appUser
     */
    @PostMapping("/add")
    @ResponseBody
    @CrossOrigin
    public AjaxResult addSave(AppUser appUser)
    {
        if(getLoginUser()==null){
            return AjaxResult.error("no login");
        }
        AppUser checkLoginName=new AppUser();
        checkLoginName.setUserLoginName(appUser.getUserLoginName());
        List<AppUser> list = appUserService.selectAppUserList(checkLoginName);
        if(list==null || list.size()==0){
            appUser.setUserPasswd(DigestUtils.md5DigestAsHex(appUser.getUserPasswd().getBytes()));
            return toAjax(appUserService.insertAppUser(appUser));
        }else {
            return AjaxResult.error("Login name already exists");
        }
    }


    /**
     * edit appUser
     */
    @PostMapping("/edit")
    @ResponseBody
    @CrossOrigin
    public AjaxResult editSave(AppUser AppUser)
    {
        if(getLoginUser()==null){
            return AjaxResult.error("no login");
        }
        if(AppUser.getUserLoginName()!=null){
            AppUser checkUser=appUserService.selectAppUserById(AppUser.getUserNo());
            if(!checkUser.getUserLoginName().equals(AppUser.getUserLoginName())){
                AppUser checkLoginName=new AppUser();
                checkLoginName.setUserLoginName(AppUser.getUserLoginName());
                List<AppUser> list = appUserService.selectAppUserList(checkLoginName);
                if(list!=null && list.size()>0){
                    return AjaxResult.error("Login name already exists");
                }
            }
        }
        return toAjax(appUserService.updateAppUser(AppUser));
    }

    /**
     * remove appUser
     */
    @PostMapping( "/remove")
    @ResponseBody
    @CrossOrigin
    public AjaxResult remove(String ids)
    {
        if(getLoginUser()==null){
            return AjaxResult.error("no login");
        }
        return toAjax(appUserService.deleteAppUserByIds(ids));
    }

    /**
     * remove appUser
     */
    @PostMapping( "/del")
    @ResponseBody
    @CrossOrigin
    public AjaxResult remove(AppUser AppUser)
    {
        if(getLoginUser()==null){
            return AjaxResult.error("no login");
        }
        return toAjax(appUserService.deleteAppUserById(AppUser.getUserNo()));
    }
}
