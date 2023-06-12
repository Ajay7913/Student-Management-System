package com.app.service;


import com.app.pojos.Admin;
import com.app.pojos.Faculty;
import com.app.pojos.User;

public interface IAdminService {

    public String addAdmin(Admin admin, User user);
    public Admin findByRegistrationId(User user);
}
