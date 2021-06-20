package fulan.tianjian.demo.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginWebAppConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor())//增加过滤的方法类
                .addPathPatterns("/**");//定义过滤的范围
    }

    @Bean
    public LoginInterceptor authenticationInterceptor() {
        return new LoginInterceptor();
    }
}
