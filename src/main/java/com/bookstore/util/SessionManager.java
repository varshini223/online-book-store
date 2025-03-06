package com.bookstore.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionManager {

    public static void createSession(HttpServletRequest request, HttpServletResponse response, int userId) {
        HttpSession session = request.getSession(true);
        session.setAttribute("userId", userId);
        session.setMaxInactiveInterval(30 * 60);
    }


    public static int getUserId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Integer userId = (Integer) session.getAttribute("userId");
            if (userId != null) {
                return userId;
            }
        }
        return -1;
    }

    public static boolean isLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && session.getAttribute("userId") != null;
    }

    public static void logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    public static void checkLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!isLoggedIn(request)) {
            response.sendRedirect("login.jsp");
        }
    }

    public static boolean isAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("userId") != null) {
            Integer userId = (Integer) session.getAttribute("userId");

            return userId == 1;
        }
        return false;
    }
}
