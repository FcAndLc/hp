package com.fc.mapper;

import com.fc.model.UserModel;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserModel record);

    int insertSelective(UserModel record);

    UserModel selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserModel record);

    int updateByPrimaryKey(UserModel record);
}