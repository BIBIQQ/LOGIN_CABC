package com.ff.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author FF
 * @date 2021/12/11
 * @TIME:14:08
 */
@Configuration
//开启 spring security
@EnableWebSecurity
//让接口可以是用注解
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 指定加密方式
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        // 使用BCrypt 加密
        return  new BCryptPasswordEncoder();
    }
    /**
     * 自定得操作
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();//开启运行iframe嵌套页面  为了解决前端页面得所需要配置得一个信息
        http//1、配置权限认证
                .authorizeRequests()
                //配置不拦截路由
                .antMatchers("/login.html").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/plugins/**").permitAll()
                .antMatchers("/login_error.html").permitAll()
                .antMatchers("/*.ico").permitAll()
                .anyRequest() //任何其它请求
                .authenticated() //都需要身份认证
                .and()
                //2、登录配置表单认证方式
                .formLogin()
                .loginPage("/login.html")//自定义登录页面的url
                .usernameParameter("username")//设置登录账号参数，与表单参数一致
                .passwordParameter("password")//设置登录密码参数，与表单参数一致
                .successForwardUrl("/main.html")//成功后跳转得页面
                .loginProcessingUrl("/login")//登录得请求
                // 告诉Spring Security在发送指定路径时处理提交的凭证，默认情况下，将用户重定向回用户来自的页面。登录表单form中action的地址，也就是处理认证请求的路径，
                // 只要保持表单中action和HttpSecurity里配置的loginProcessingUrl一致就可以了，也不用自己去处理，它不会将请求传递给Spring MVC和您的控制器，所以我们就不需要自己再去写一个/user/login的控制器接口了
                .defaultSuccessUrl("/main.html")//登录成功后默认的跳转页面路径
                .failureUrl("/login_error.html")
                .and()
                //3、注销
                .logout()
                .logoutUrl("/logout") //内置退出接口
                .permitAll()
                .and()
                //4、session管理
                .sessionManagement()
                .invalidSessionUrl("/login.html") //失效后跳转到登陆页面
                //单用户登录，如果有一个登录了，同一个用户在其他地方登录将前一个剔除下线
                //.maximumSessions(1).expiredSessionStrategy(expiredSessionStrategy())
                //单用户登录，如果有一个登录了，同一个用户在其他地方不能登录
                //.maximumSessions(1).maxSessionsPreventsLogin(true) ;
                .and()
                //5、禁用跨站csrf攻击防御
                .csrf() //静态html 必须配置
                .disable();
    }
}
