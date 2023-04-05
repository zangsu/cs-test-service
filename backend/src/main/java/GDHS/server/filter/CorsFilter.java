package GDHS.server.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

import GDHS.server.constant.HttpConst;
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
	private static HashSet<String> alloweOrigin = new HashSet<>();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);

		alloweOrigin.add("http://localhost:3000");
		alloweOrigin.add("https://cs-test-service-afno7k1gh-moon-gd.vercel.app");

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws
		IOException,
		ServletException {

		log.info("launch CorsFilter.doFilter");
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;


		String origin = request.getHeader(HttpConst.ORIGIN);

		if(alloweOrigin.contains(origin) == false)
			return;
		response.setHeader("Access-Control-Allow-Origin", origin);
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "*");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");

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
