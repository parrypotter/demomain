package com.panrui.panrui.mapper;

import com.panrui.panrui.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository {
    User findByUserName(String username);
}
