package com.shopping.cosmos.user.controller;

import com.shopping.cosmos.user.service.UserService_jo;
import com.shopping.cosmos.user.vo.UserSearchVO_jo;
import com.shopping.cosmos.user.vo.UserVO_jo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/cos")
public class UserListController_jo {

    @Autowired
    UserService_jo service;

    // 유저목록 가져오기
    @GetMapping("/manager/userList/{pageNum}")
    List<UserVO_jo> getUserList(UserVO_jo user) {
        System.out.println("getUserList접근");
        // 만약 페이지가 없으면 1을 넣어줌
        try {
            if (user.getPageNum() == 0)
                user.setPageNum(1);
            // 상품 몇번쨰부터 보여줄건지 계산
            int startRow = (user.getPageNum() - 1) * 10 + 1;
            // 10개씩 보여줌
            int endRow = startRow + 9;
            user.setStartRow(startRow);
            user.setEndRow(endRow);
            System.out.println(startRow);
            System.out.println(endRow);

            return service.getUserList(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/manager/userCount")
    int getUserCount(UserVO_jo vo) {
        try {
            // 전체상품개수
            int userCount = service.userCount();
            // 상품페이지를 보여주기위해 10으로 나눈값을 하나더함 상품개수가 33개라면 3페이지가 아닌 4페이지를 보여주기위해
            if (userCount > 10 && userCount % 10 != 0) {
                userCount = userCount / 10 + 1;
            } else {
                userCount = userCount / 10;

            }
            if (userCount == 0) {
                userCount = 1;
            }
            System.out.println(userCount);
            return userCount;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    // 유저검색
    @GetMapping("/manager/userSearch/{keyword}/{searchType}")
    List<UserSearchVO_jo> userSearch(UserSearchVO_jo user) {
        System.out.println("userSearch접근");
        System.out.println(user);
        try {
            return service.userSearch(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
