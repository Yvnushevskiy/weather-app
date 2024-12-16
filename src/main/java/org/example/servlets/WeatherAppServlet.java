//package org.example.servlets;
//
//import jakarta.servlet.ServletConfig;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.context.Context;
//
//import java.io.IOException;
////TODO в конце проекта пройтись через mvn dependency:analyze
////TODO need change all if(boolean) to Try catch for redirect
//
//@WebServlet("/")
//public class WeatherAppServlet extends HttpServlet {
//
//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        //init если нужно накидать что будет инициализироваться вместе с сервлетом Todo
//        super.init(config);
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        TemplateEngine templateEngine = (TemplateEngine) getServletContext().getAttribute("templateEngine");
//
//        if (templateEngine == null) {
//            resp.getWriter().write("Cant find HTML file");
//        }else {
//            Context context = new Context();
//            templateEngine.process("home", context, resp.getWriter());
//        }
//    }
//}
