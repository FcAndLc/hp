package com.fc.weixin.service.impl;

import com.fc.weixin.mapper.UsersMapper;
import com.fc.weixin.model.UserModel;
import com.fc.weixin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;
    @Override
    public void insertUser(UserModel userModel) {
        usersMapper.insertSelective(userModel);
    }
}
