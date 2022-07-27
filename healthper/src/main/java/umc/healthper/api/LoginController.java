package umc.healthper.api;


import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import umc.healthper.domain.login.LoginArgs;
import umc.healthper.domain.login.LoginService;
import umc.healthper.domain.login.SessionConst;
import umc.healthper.domain.login.model.KakaoId;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/home")
    public String openApp(){
        return "redirect:"+ LoginArgs.kakao_auth+"?client_id="+
                LoginArgs.api_key+"&redirect_uri="+LoginArgs.redirect_uri+"&response_type=code";
    }

    @GetMapping("/login")
    public String login(@RequestParam("code")String code,
                        @RequestParam(defaultValue = "/routines") String redirectURL,HttpServletRequest request) throws JsonProcessingException {
        Long loginId = loginService.kakoLogin(code);
        if(loginId == null){
            return "redirect:/home";
        }
        HttpSession session = request.getSession();
        //세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginId);

        return "redirect:"+redirectURL;
    }
}
