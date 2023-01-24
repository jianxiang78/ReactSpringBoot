package com.auto17.controller;

import com.auto17.domain.AppUser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.auto17.domain.AjaxResult;
import com.auto17.page.Constants;
import com.auto17.page.PageDomain;
import com.auto17.page.TableDataInfo;
import com.auto17.page.TableSupport;
import com.auto17.utils.ServletUtils;
import com.auto17.utils.SqlUtil;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Base Controller
 *
 * @author
 */
public class BaseController
{
    /**
     * start Page
     */
    protected void startPage()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (!StringUtils.isEmpty(pageNum) && !StringUtils.isEmpty(pageSize))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    /**
     * start Order By
     */
    protected void startOrderBy()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        if (!StringUtils.isEmpty(pageDomain.getOrderBy()))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.orderBy(orderBy);
        }
    }

    /**
     * get Request
     */
    public HttpServletRequest getRequest()
    {
        return ServletUtils.getRequest();
    }

    /**
     * get Response
     */
    public HttpServletResponse getResponse()
    {
        return ServletUtils.getResponse();
    }

    /**
     * get Session
     */
    public HttpSession getSession()
    {
        return getRequest().getSession();
    }

    /**
     * get Data Table page
     */
    protected TableDataInfo getDataTable(List<?> list)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(Constants.intSUCCESS);
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * Response Result
     *
     * @param rows
     * @return AjaxResult
     */
    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? success() : error();
    }

    /**
     * Response
     *
     * @param result
     * @return AjaxResult
     */
    protected AjaxResult toAjax(boolean result)
    {
        return result ? success() : error();
    }

    /**
     * Response success
     */
    public AjaxResult success()
    {
        return AjaxResult.success();
    }

    /**
     * Response error
     */
    public AjaxResult error()
    {
        return AjaxResult.error();
    }

    /**
     * Response success
     */
    public AjaxResult success(String message)
    {
        return AjaxResult.success(message);
    }

    public AjaxResult success(String message,Object data)
    {
        return AjaxResult.success(message,data);
    }

    public AjaxResult error(String message)
    {
        return AjaxResult.error(message);
    }

    public AjaxResult error(String message,Object data)
    {
        return AjaxResult.error(message,data);
    }

    public AppUser getLoginUser(){
        return (AppUser) getRequest().getAttribute("loginUser");
    }
}
