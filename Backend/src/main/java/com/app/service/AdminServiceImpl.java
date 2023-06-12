package com.app.service;


import com.app.dao.AdminRepository;
import com.app.pojos.Admin;
import com.app.pojos.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements IAdminService{

    @Autowired
    private AdminRepository adminRepository;


    @Override
    public String addAdmin(Admin admin, User user) {
        admin.setUser(user);
        adminRepository.save(admin);
        return "Admin added successfully";
    }

    @Override
    public Admin findByRegistrationId(User user){
        return adminRepository.findByRegistrationId(user).orElseThrow(()-> new RuntimeException("ADMIN NOT FOUND"));
    }
}
