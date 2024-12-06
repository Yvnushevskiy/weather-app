package org.example.servlets;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
//TODO delete this class
import java.io.IOException;


@WebServlet ("/example")
public class ThymeleafServlet extends HttpServlet {

    private TemplateEngine templateEngine;

    @Override
    public void init() throws ServletException {
        // Настроим Thymeleaf
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates\\");
        templateResolver.setSuffix(".html");

        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Создаем контекст с данными
        Context context = new Context();
        context.setVariable("name", "Алексей");  // Здесь передаем переменную name

        // Рендерим шаблон и отправляем на клиент
        response.setContentType("text/html");
        templateEngine.process("index", context, response.getWriter());  // имя шаблона: index.html
    }
}
