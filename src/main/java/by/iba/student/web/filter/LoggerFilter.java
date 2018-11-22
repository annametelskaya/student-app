package by.iba.student.web.filter;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

public class LoggerFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        ObjectMapper mapper = new ObjectMapper();
        chain.doFilter(request, response);
        if ("GET".equals(req.getMethod())) {
            Object items = req.getAttribute("items");
            PrintWriter pw = response.getWriter();
            pw.print(mapper.writeValueAsString(items));
            pw.close();
        }
    }

    @Override
    public void destroy() {

    }
}
