package com.example.filterinterceptoraop.config.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

@Slf4j
public class LoggingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("[LOGGING FILTER] init");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("[LOGGING FILTER] before doFilter");
        chain.doFilter(request, response);
        log.info("[LOGGING FILTER] after doFilter");
    }

    @Override
    public void destroy() {
        log.info("[LOGGING FILTER] destroy");
        Filter.super.destroy();
    }
}
