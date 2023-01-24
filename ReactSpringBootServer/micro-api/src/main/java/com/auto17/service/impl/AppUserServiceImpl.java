package com.auto17.service.impl;

import java.util.List;

import com.auto17.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.auto17.mapper.AppUserMapper;
import com.auto17.domain.AppUser;
import com.auto17.service.IAppUserService;

/**
 * appUserService
 *
 * @author jianxiang sun
 * @date 2022-12-02
 */
@Service
public class AppUserServiceImpl implements IAppUserService
{
    @Autowired
    private AppUserMapper AppUserMapper;

    /**
     * Query appUser
     *
     * @param userNo appUserID
     * @return appUser
     */
    @Override
    public AppUser selectAppUserById(Long userNo)
    {
        return AppUserMapper.selectAppUserById(userNo);
    }


    @Override
    public AppUser selectAppUserOne(AppUser appUser)
    {
        List<AppUser> list=AppUserMapper.selectAppUserList(appUser);
        if(list!=null && list.size()>0){
            return list.get(0);
        }else {
            return null;
        }
    }
    /**
     * Query appUser list
     *
     * @param appUser appUser
     * @return appUser
     */
    @Override
    public List<AppUser> selectAppUserList(AppUser appUser)
    {
        return AppUserMapper.selectAppUserList(appUser);
    }

    /**
     * add appUser
     *
     * @param appUser appUser
     * @return
     */
    @Override
    public int insertAppUser(AppUser appUser)
    {
        return AppUserMapper.insertAppUser(appUser);
    }

    /**
     * edit appUser
     *
     * @param appUser appUser
     * @return
     */
    @Override
    public int updateAppUser(AppUser appUser)
    {
        return AppUserMapper.updateAppUser(appUser);
    }

    /**
     * delete appUser object
     *
     * @param ids need  delete ID
     * @return
     */
    @Override
    public int deleteAppUserByIds(String ids)
    {
        return AppUserMapper.deleteAppUserByIds(Convert.toStrArray(ids));
    }

    /**
     * delete appUser
     *
     * @param userNo appUserID
     * @return
     */
    @Override
    public int deleteAppUserById(Long userNo)
    {
        return AppUserMapper.deleteAppUserById(userNo);
    }
}
