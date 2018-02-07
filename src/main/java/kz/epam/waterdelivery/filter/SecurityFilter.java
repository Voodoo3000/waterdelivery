package kz.epam.waterdelivery.filter;

import kz.epam.waterdelivery.entity.Entity;
import kz.epam.waterdelivery.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SecurityFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(SecurityFilter.class);
    private List<String> adminPages = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        adminPages.add("/admin_orders");
        adminPages.add("/admin_water");
        adminPages.add("/admin_bottle");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        doFilter0((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    private void doFilter0(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        String pathInfo = req.getPathInfo();
        User user = (User) session.getAttribute(Entity.ATTR_USER);
        User.Role currentRole = null;
        if (user != null) currentRole = user.getRole();
        if ((pathInfo != null) && (currentRole != User.Role.ADMIN)) {
            for (String page : adminPages) {
                if (pathInfo.contains(page)) {
                    LOGGER.error("Access denied. Page: " + pathInfo + " required admin privileges");
                    resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Have not required privileges. Access denied.");
                    return;
                }
            }
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
