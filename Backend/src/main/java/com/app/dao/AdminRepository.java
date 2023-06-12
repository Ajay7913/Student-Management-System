package com.app.dao;


import com.app.pojos.Admin;
import com.app.pojos.Faculty;
import com.app.pojos.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByRegistrationId(User user);


}
