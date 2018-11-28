package springcloud.springboot.config;

import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(
        filterName = "MyFilter",
        urlPatterns = "/*"
)
@Configuration /* spring boot 中需要次注解或手动加载bean才能生效*/
public class MyFilter implements javax.servlet.Filter {
    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("MyFilter执行 初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println("MyFilter执行 当前请求地址：" + request.getRequestURI());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("MyFilter执行 销毁完成");
    }
}
