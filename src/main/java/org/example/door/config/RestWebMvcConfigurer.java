package org.example.door.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class RestWebMvcConfigurer implements WebMvcConfigurer {

    /**
     *  允许跨域访问
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
                // 可限制哪个请求可以通过跨域
        registry.addMapping("/**")
                // 可限制固定请求头可以通过跨域
                .allowedHeaders("*")
                // 可限制固定methods可以通过跨域
                .allowedMethods("*")
                // 可限制访问ip可以通过跨域,SpringBoot2.4.0 [allowedOriginPatterns]代替[allowedOrigins]
                .allowedOriginPatterns("*")
                // 是否允许发送cookie
                .allowCredentials(true)
                .exposedHeaders(HttpHeaders.SET_COOKIE);
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}