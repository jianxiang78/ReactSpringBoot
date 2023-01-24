package com.auto17.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * appUser object ot_app_user
 *
 * @author jianxiang sun
 * @date 2022-12-02
 */
public class AppUser
{
    private static final long serialVersionUID = 1L;

    /** User No */
    private Long userNo;

    /** user login name,nickname */
    private String userLoginName;

    /** user login password */
    private String userPasswd;

    /** user true name */
    private String userTrueName;

    /** 0,admin user;1,manager;2,operator */
    private String userRole;

    public void setUserNo(Long userNo)
    {
        this.userNo = userNo;
    }

    public Long getUserNo()
    {
        return userNo;
    }
    public void setUserLoginName(String userLoginName)
    {
        this.userLoginName = userLoginName;
    }

    public String getUserLoginName()
    {
        return userLoginName;
    }
    public void setUserPasswd(String userPasswd)
    {
        this.userPasswd = userPasswd;
    }

    public String getUserPasswd()
    {
        return userPasswd;
    }
    public void setUserTrueName(String userTrueName)
    {
        this.userTrueName = userTrueName;
    }

    public String getUserTrueName()
    {
        return userTrueName;
    }
    public void setUserRole(String userRole)
    {
        this.userRole = userRole;
    }

    public String getUserRole()
    {
        return userRole;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userNo", getUserNo())
            .append("userLoginName", getUserLoginName())
            .append("userPasswd", getUserPasswd())
            .append("userTrueName", getUserTrueName())
            .append("userRole", getUserRole())
            .toString();
    }
}
