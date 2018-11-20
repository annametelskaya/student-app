package by.iba.student.web.filter;

import by.iba.student.common.Group;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class LoggerFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (req.getMethod().equals("GET")) {
            chain.doFilter(request, response);
            List<Object> items = (List<Object>) req.getAttribute("items");
            ObjectMapper mapper = new ObjectMapper();
            PrintWriter pw = response.getWriter();
            pw.print(mapper.writeValueAsString(items));
            pw.close();
        }
        if (req.getMethod().equals("POST")) {
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");
            StringBuilder sb = new StringBuilder();
            BufferedReader br = req.getReader();
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            req.setAttribute("strPost", sb);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
