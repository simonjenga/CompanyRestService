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

}