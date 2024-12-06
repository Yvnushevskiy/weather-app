package org.example.authFilter;

import com.fasterxml.jackson.databind.deser.impl.CreatorCandidate;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.web.IWebExchange;

import java.io.File;
import java.io.IOException;
@WebFilter
public class ThymeleafFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".html");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        filterConfig.getServletContext().setAttribute("templateEngine", templateEngine);


    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        response.setContentType("text/html");
        chain.doFilter(request, response);
    }
}
