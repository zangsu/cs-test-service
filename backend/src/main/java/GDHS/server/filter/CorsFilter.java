package GDHS.server.filter;

import java.io.IOException;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestHeader;

@Slf4j
@Component
public class CorsFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws
		IOException,
		ServletException {

		log.info("launch CorsFilter.doFilter");
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;

		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
		}else{
			chain.doFilter(req, res);
		}


	}

	@Override
	public void destroy() {
		Filter.super.destroy();
	}
}
