package com.huagongwuliu.waybillelectronic.config;

import com.huagongwuliu.waybillelectronic.constant.Constants;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class TokenFilter extends OncePerRequestFilter {

    private static final PathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            if(isProtectedUrl(request)) {
                String token = request.getHeader("Authorization");
                //对token进行检查
                validateToken(token);
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        }
        //如果jwt令牌通过了检测, 那么就把request传递给后面的RESTful api
        filterChain.doFilter(request, response);
    }

    //只对地址 /waybill 开头的
    private boolean isProtectedUrl(HttpServletRequest request) {
        String url = "/waybill/**";
        boolean match = pathMatcher.match(url, request.getServletPath());

        return match;
    }

    private void  validateToken(String token){
        if (!Constants.DEFAULT_TOKEN.equals(token)){
            throw new IllegalStateException("Invalid Token:"+token);
        }
    }


}
