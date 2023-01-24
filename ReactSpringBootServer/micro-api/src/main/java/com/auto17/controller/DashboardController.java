package com.auto17.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
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
 * dashboard
 *
 * @author jianxiang sun
 */

@RestController
@RequestMapping("/dashboard")
@CrossOrigin
public class DashboardController extends BaseController
{
    protected final Logger logger = LoggerFactory.getLogger(DashboardController.class);

    @PostMapping("/demo")
    public AjaxResult demo() {
        if(getLoginUser()==null){
            return AjaxResult.error("no login");
        }
        JSONObject object=new JSONObject();
        object.put("progressData",progressExample());
        object.put("progressGroupData1",progressGroupExample1());
        object.put("progressGroupData2",progressGroupExample2());
        object.put("progressGroupData3",progressGroupExample3());
        return AjaxResult.success(object);
    }

    private JSONArray progressExample(){
        String jsonArrayStr ="[{ title: 'Visits', value: '29.703 Users', percent: 40, color: 'success' },\n" +
                "    { title: 'Unique', value: '24.093 Users', percent: 20, color: 'info' },\n" +
                "    { title: 'Pageviews', value: '78.706 Views', percent: 60, color: 'warning' },\n" +
                "    { title: 'New Users', value: '22.123 Users', percent: 80, color: 'danger' },\n" +
                "    { title: 'Bounce Rate', value: 'Average Rate', percent: 40.15, color: 'primary' }]";
        JSONArray progress=JSONArray.parseArray(jsonArrayStr);
        return progress;
    }

    private JSONArray progressGroupExample1(){
        String jsonArrayStr ="[\n" +
                "    { title: 'Monday', value1: 34, value2: 78 },\n" +
                "    { title: 'Tuesday', value1: 56, value2: 94 },\n" +
                "    { title: 'Wednesday', value1: 12, value2: 67 },\n" +
                "    { title: 'Thursday', value1: 43, value2: 91 },\n" +
                "    { title: 'Friday', value1: 22, value2: 73 },\n" +
                "    { title: 'Saturday', value1: 53, value2: 82 },\n" +
                "    { title: 'Sunday', value1: 9, value2: 69 },\n" +
                "  ]";
        return JSONArray.parseArray(jsonArrayStr);
    }

    private JSONArray progressGroupExample2(){
        String jsonArrayStr ="[\n" +
                "    { title: 'Male', icon: 'cilUser', value: 53 },\n" +
                "    { title: 'Female', icon: 'cilUserFemale', value: 43 },\n" +
                "  ]";
        return JSONArray.parseArray(jsonArrayStr);
    }

    private JSONArray progressGroupExample3(){
        String jsonArrayStr ="[\n" +
                "    { title: 'Organic Search', icon: 'cibGoogle', percent: 56, value: '191,235' },\n" +
                "    { title: 'Facebook', icon: 'cibFacebook', percent: 15, value: '51,223' },\n" +
                "    { title: 'Twitter', icon: 'cibTwitter', percent: 11, value: '37,564' },\n" +
                "    { title: 'LinkedIn', icon: 'cibLinkedin', percent: 8, value: '27,319' },\n" +
                "  ]";
        return JSONArray.parseArray(jsonArrayStr);
    }

}
