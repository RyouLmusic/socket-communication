package com.hbr.weChat.mapper;

import com.hbr.weChat.model.Friends;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface FriendsMapper {
    List<Friends> select(@Param("account") String account);
}
