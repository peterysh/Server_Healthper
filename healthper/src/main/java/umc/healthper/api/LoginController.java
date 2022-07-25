package umc.healthper.api;


import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import umc.healthper.domain.login.LoginService;
import umc.healthper.domain.login.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/login")
    @ResponseBody
    public String login(@RequestParam("code")String code, HttpServletRequest request) throws JsonProcessingException {
        Long loginMember = loginService.kakoLogin(code);
        if(loginMember == null){
            return "failed";
        }
        HttpSession session = request.getSession();
        //세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "ok " + session.getAttribute(SessionConst.LOGIN_MEMBER);
    }
}
