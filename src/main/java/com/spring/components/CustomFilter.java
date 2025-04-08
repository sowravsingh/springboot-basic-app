package com.spring.components;

import javax.servlet.*;
import java.io.IOException;

public class CustomFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("inside custom filter ");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("completed my custom filter");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
