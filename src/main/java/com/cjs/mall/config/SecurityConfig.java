package com.cjs.mall.config;

import com.cjs.mall.component.JwtAuthenticationTokenFilter;
import com.cjs.mall.component.RestAuthenticationEntryPoint;
import com.cjs.mall.component.RestfulAccessDeniedHandler;
import com.cjs.mall.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @description: springSecurity的配置
 * @author: cuijunsheng
 * @date: 2020-04-03 15:13
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UmsAdminService adminService;

    @Autowired
    private RestAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private RestfulAccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf() //由于使用的是JWT,这里不需要使用csrf
                .disable()
                .sessionManagement() //基于token，不需要session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(
                        //允许网站对静态资源的无授权访问
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/swagger-resources/**",
                        "/v2/api-docs/**")
                .permitAll()

                //对登录，注册放行
                .antMatchers("/admin/login", "/admin/register")
                .permitAll()

                //跨域请求会先调用一次options请求
                .antMatchers(HttpMethod.OPTIONS)
                .permitAll()

                //测试时全部放行
                .antMatchers("/**")
                .permitAll()

                //除上面的请求外全部需要鉴权认证
                .anyRequest()
                .authenticated();

        //禁用缓存
        httpSecurity.headers().cacheControl();

        //添加jwt filter
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        //添加自定义的未授权和未登录返回结果
        httpSecurity.exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPoint);
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {

        return new JwtAuthenticationTokenFilter();
    }
}
