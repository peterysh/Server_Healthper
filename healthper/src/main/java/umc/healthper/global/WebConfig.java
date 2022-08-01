package umc.healthper.global;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import umc.healthper.domain.login.LoginCheckInterceptor;
import umc.healthper.global.argumentresolver.LoginMemberArgResolver;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override//개무서워
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArgResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/home","/member/add","/login","logout",
                        "/css/**","/*.ico","/error");
    }
}
