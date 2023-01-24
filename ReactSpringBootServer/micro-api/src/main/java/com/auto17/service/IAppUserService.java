package com.auto17.service;

import java.util.List;
import java.util.Map;

import com.auto17.domain.AppUser;

/**
 * appUserService
 *
 * @author jianxiang sun
 * @date 2022-12-02
 */
public interface IAppUserService
{
    /**
     * Query appUser
     *
     * @param userNo appUserID
     * @return appUser
     */
    public AppUser selectAppUserById(Long userNo);

    public AppUser selectAppUserOne(AppUser appUser);
    /**
     * Query appUser list
     *
     * @param appUser appUser
     * @return appUser集合
     */
    public List<AppUser> selectAppUserList(AppUser appUser);

    /**
     * add appUser
     *
     * @param appUser appUser
     * @return
     */
    public int insertAppUser(AppUser appUser);

    /**
     * edit appUser
     *
     * @param appUser appUser
     * @return
     */
    public int updateAppUser(AppUser appUser);

    /**
     * more delete appUser
     *
     * @param ids need  delete ID
     * @return
     */
    public int deleteAppUserByIds(String ids);

    /**
     * delete appUser
     *
     * @param userNo appUserID
     * @return
     */
    public int deleteAppUserById(Long userNo);
}
