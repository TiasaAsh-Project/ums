package com.indusnet.ums.service;


import com.indusnet.ums.common.ResponseModel;
import com.indusnet.ums.model.UserModel;

import jakarta.validation.Valid;

public interface IUserService {

	ResponseModel add(@Valid UserModel model);

    String get(String id);

    String getAllUsers();

    //ResponseModel deleteUser(String id);

    //String updateUser(String id, UserModel fromJson);

    ResponseModel update(UserModel fromJson);

    ResponseModel delete(String id);

    String getName(String name);

    String getEmail(String email);

    String getPassword(String password);

    //void delete(String id);
}
