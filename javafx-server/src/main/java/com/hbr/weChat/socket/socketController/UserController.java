package com.hbr.weChat.socket.socketController;

import com.hbr.weChat.dto.ResponseCode;
import com.hbr.weChat.model.Friends;
import com.hbr.weChat.model.User;
import com.hbr.weChat.service.FriendsService;
import com.hbr.weChat.service.UserService;
import com.hbr.weChat.socket.ManageServerSocket;
import com.hbr.weChat.socket.SocketUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    FriendsService friendsService;

    public void sendToClient(User user) {
        ResponseCode backCode = new ResponseCode();
        User ufDB;
        ufDB = userService.select(user.getAccount(), user.getPassword());
        System.out.println(ufDB);
        List<User> users = new ArrayList<>();
        if(ufDB != null){
            ufDB.setStatus(1);
            userService.updateStatus(ufDB);
            backCode.setCode(ResponseCode.SUCCESS);


            System.out.println(friendsService.Select(ufDB.getAccount()));
            List<Friends> friends = friendsService.Select(ufDB.getAccount());

            for (Friends friend: friends){
                users.add(friend.getUser());
            }

        }else {
            backCode.setCode(ResponseCode.FALL);
        }
        backCode.setUsers(users);
        backCode.setOwnInfo(ufDB);
        SocketUtil.send(ManageServerSocket.getServerSocketThread(user.getAccount()).getSocket(), backCode);
    }

}
