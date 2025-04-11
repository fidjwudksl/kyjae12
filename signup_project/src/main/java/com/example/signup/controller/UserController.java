package com.example.signup.controller;

import com.example.signup.dto.UpdateRequest;
import com.example.signup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 닉네임 수정
    @PutMapping("/updateNickname")
    public String updateNickname(@RequestBody UpdateRequest updateRequest) {
        String nickname = updateRequest.getNickname();
        // 유효성 검사를 할 수 있습니다. 예를 들어, 비어있지 않은지 체크 등
        if (nickname == null || nickname.trim().isEmpty()) {
            return "닉네임을 입력해 주세요.";
        }

        // 비즈니스 로직으로 닉네임을 업데이트하는 서비스 메서드 호출
        boolean updated = userService.updateNickname(nickname);

        if (updated) {
            return "닉네임이 성공적으로 변경되었습니다.";
        } else {
            return "닉네임 수정 실패";
        }
    }

    // 비밀번호 수정
    @PutMapping("/updatePassword")
    public String updatePassword(@RequestBody UpdateRequest updateRequest) {
        String password = updateRequest.getPassword();
        // 비밀번호 유효성 검사
        if (password == null || password.trim().isEmpty()) {
            return "비밀번호를 입력해 주세요.";
        }

        // 비즈니스 로직으로 비밀번호를 업데이트하는 서비스 메서드 호출
        boolean updated = userService.updatePassword(password);

        if (updated) {
            return "비밀번호가 성공적으로 변경되었습니다.";
        } else {
            return "비밀번호 수정 실패";
        }
    }
}
