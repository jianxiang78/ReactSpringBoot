package com.auto17.mapper;

import java.util.List;
import java.util.Map;

import com.auto17.domain.AppUser;
import org.springframework.stereotype.Component;

/**
 * appUserMapper
 *
 * @author jianxiang sun
 * @date 2022-12-02
 */
@Component
public interface AppUserMapper
{
    /**
     * Query appUser
     *
     * @param userNo appUserID
     * @return appUser
     */
    public AppUser selectAppUserById(Long userNo);

    /**
     * Query appUser list
     *
     * @param appUser appUser
     * @return appUser list
     */
    public List<AppUser> selectAppUserList(AppUser appUser);

    public List<Map> selectAppUserOperatorList(AppUser appUser);
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
     * delete appUser
     *
     * @param userNo appUserID
     * @return
     */
    public int deleteAppUserById(Long userNo);

    /**
     * more delete appUser
     *
     * @param userNos need  delete ID
     * @return
     */
    public int deleteAppUserByIds(String[] userNos);
}
