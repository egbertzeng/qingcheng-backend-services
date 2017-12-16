/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.common.configration.filters;//package com.qingcheng.common.configration.filters;

/**
 * Created by liguohua on 6/1/16.
 */

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
public class MyFilter4CORS implements Filter {
    public void init(FilterConfig filterConfig) {
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,OPTIONS");
        response.setHeader("Access-Control-Max-Age", "8640000");
        response.setHeader("Access-Control-Allow-Headers", "Origin,Accept-Ranges, X-Requested-With, Content-Type, Accept,Authorization");
        chain.doFilter(req, res);
    }

    public void destroy() {
    }

}