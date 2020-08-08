package com.ruban.controller;

import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Pattern;

@Component
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session =httpServletRequest.getSession();

        Pattern pattern = Pattern.compile(".*\\.css");
        boolean isStatic = pattern.matcher(httpServletRequest.getRequestURI()).matches();
        if (session.getAttribute("login") == null &&
                !"/login".equals(httpServletRequest.getRequestURI()) && !isStatic) {
            httpServletResponse.sendRedirect("/login");
        } else {
            chain.doFilter(request, response);
        }
    }
}
