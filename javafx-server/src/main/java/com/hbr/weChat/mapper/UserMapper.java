package com.hbr.weChat.mapper;

import com.hbr.weChat.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    User select(@Param("account") String account, @Param("password") String password);

    void addUser(User user);

    void updateStatus(User user);
}
