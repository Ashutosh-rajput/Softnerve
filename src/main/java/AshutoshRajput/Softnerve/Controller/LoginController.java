package AshutoshRajput.Softnerve.Controller;

import AshutoshRajput.Softnerve.DTO.LoginDTO;
import AshutoshRajput.Softnerve.Security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@EnableMethodSecurity
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    public JwtService jwtService;

//    @Autowired
//    private LoginServiceImpl loginService;

    @PostMapping("/autologin")
    public String login(@RequestBody LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(loginDTO.getEmail());
        }
        else {
            throw  new UsernameNotFoundException("invalid User Request");
        }


//        String token= loginService.login(loginDto);
//        JwtAuthResponseDto jwtAuthResponseDto=new JwtAuthResponseDto();
//        jwtAuthResponseDto.setAccessToken(token);
//        return new ResponseEntity<>(jwtAuthResponseDto, HttpStatus.ACCEPTED);

    }
}
