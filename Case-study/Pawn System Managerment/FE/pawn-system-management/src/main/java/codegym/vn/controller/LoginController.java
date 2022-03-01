package codegym.vn.controller;

import codegym.vn.config_sercurity.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping(value = "/login")
    public ResponseEntity<String> doLogin(@Param("userName") String userName, @Param("password") String password){
        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        String jwt = jwtTokenUtil.generateJwtToken(userName);
        System.out.println("Token " + jwt);
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }

    @GetMapping("/random")
    public ResponseEntity<String> randomStuff(){
        return new ResponseEntity<>("JWT Hợp lệ mới có thể thấy được message này", HttpStatus.OK);
    }

}
