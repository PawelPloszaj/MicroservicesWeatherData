package com.pbs.edu.webapp.config;

import io.github.bucket4j.*;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitingFilter implements Filter {
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String ip = httpRequest.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty()) {
            ip = httpRequest.getRemoteAddr();
        } else {
            ip = ip.split(",")[0].trim();
        }

        buckets.putIfAbsent(ip, Bucket4j.builder()
                .addLimit(Bandwidth.classic(10, Refill.intervally(10, Duration.ofSeconds(10))))
                .build());

        Bucket bucket = buckets.get(ip);
        System.out.println("IP: " + ip + " - Tokens left: " + bucket.getAvailableTokens());

        if (!bucket.tryConsume(1)) {
            ((HttpServletResponse) response).setStatus(429);
            response.getWriter().write("429 Too Many Requests");
            return;
        }
        chain.doFilter(request, response);
    }
}

