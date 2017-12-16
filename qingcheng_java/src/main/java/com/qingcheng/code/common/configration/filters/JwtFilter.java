package com.qingcheng.code.common.configration.filters;

/**
 * Created by liguohua on 2017/5/4.
 */

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.qingcheng.code.common.configration.filters.JwtTokenService.parseJwtToken;

public class JwtFilter extends GenericFilterBean {
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
            throws IOException, ServletException {
        //convert type
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        //convert type
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(request, response);
        } else {
             String token = request.getHeader("Authorization");
            if (token==null||"".equals(token)){
                throw new RuntimeException("Authorization不能为空");
            }

            Object o = parseJwtToken(token);
            request.setAttribute("claims", o);
            chain.doFilter(request, response);
        }
    }
}