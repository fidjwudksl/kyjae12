package com.example.signup.service;

import com.example.signup.entity.User;
import com.example.signup.repository.UserRepository;
import com.example.signup.repository.VerificationCodeRepository;
import com.example.signup.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import com.example.signup.dto.SignupRequest;
import com.example.signup.entity.User;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final VerificationCodeRepository codeRepository;
    private final JwtTokenUtil jwtTokenUtil;

    public String authenticateUser(String username, String password) {
    System.out.println("로그인 시도 - username: " + username + ", password: " + password);
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
    System.out.println("사용자를 찾을 수 없습니다.");
    System.out.println("비밀번호 불일치");
    return null;
}
User user = optionalUser.get();
System.out.println("DB 저장 비밀번호: " + user.getPassword());
if (password.equals(user.getPassword())) {
    System.out.println("비밀번호 일치");
            return jwtTokenUtil.generateToken(username);
        }
        System.out.println("비밀번호 불일치");
    return null;
    }

    // TODO: 나머지 회원가입 및 인증 관련 로직들 추가

    
    public ResponseEntity<String> registerUser(SignupRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword()); // WARNING: 비밀번호 암호화 제거됨 (테스트용)
        user.setNickname(request.getNickname());
        user.setDiscordId(request.getDiscordId());
        user.setCreatedAt(java.time.LocalDateTime.now());

        userRepository.save(user);
        return org.springframework.http.ResponseEntity.ok("회원가입 완료");
    }


    public boolean updateNickname(String nickname) {
        // 데이터베이스에서 해당 사용자의 닉네임을 업데이트하는 로직을 구현
        // 예시: userRepository.updateNickname(userId, nickname);
        // 성공적으로 업데이트되었으면 true, 실패하면 false를 반환
        return true;  // 실제 로직에서는 데이터베이스 연동을 해야 합니다.
    }

    // 비밀번호 수정 로직
    public boolean updatePassword(String password) {
        // 데이터베이스에서 해당 사용자의 비밀번호를 업데이트하는 로직을 구현
        // 예시: userRepository.updatePassword(userId, password);
        // 성공적으로 업데이트되었으면 true, 실패하면 false를 반환
        return true;  // 실제 로직에서는 데이터베이스 연동을 해야 합니다.
    }


}