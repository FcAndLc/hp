package com.fc.service.impl;

import com.fc.mapper.UsersMapper;
import com.fc.model.UserModel;
import com.fc.service.UserService;
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
