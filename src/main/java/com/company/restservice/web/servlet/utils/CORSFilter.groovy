package com.company.restservice.web.servlet.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;

/**
 * <p>
 * A {@link Filter} that enable client-side cross-origin requests by implementing W3C's
 * CORS (<b>C</b>ross-<b>O</b>rigin <b>R</b>esource <b>S</b>haring) specification for resources.
 *
 * Each {@link HttpServletRequest} request is inspected as per specification, and appropriate
 * response headers are added to {@link HttpServletResponse}.
 * </p>
 * 
 * @author Simon Njenga
 * @version 0.1
 */
public class CORSFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
        throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "POST, GET, PUT");
        response.setHeader(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "3600");
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "X-Requested-With, Content-Type");

        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {
        // Intentionally empty!
    }

    public void destroy() {
        // Intentionally empty!
    }
}