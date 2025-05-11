package az.msjobgateway.filter;

import az.msjobgateway.exception.UnauthorizedException;
import az.msjobgateway.util.JwtUtil;
import com.netflix.spectator.impl.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final RouteValidator routeValidator;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthenticationFilter(RouteValidator routeValidator, JwtUtil jwtUtil) {
        super(Config.class);
        this.routeValidator = routeValidator;
        this.jwtUtil = jwtUtil;
    }

    public static class Config {

    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            String username;
            String token = null;
            String role = null;
            ServerWebExchange modifiedExchange = null;

            if (routeValidator.isSecured.test(exchange.getRequest())) {
                String authHeader = exchange.getRequest().getHeaders().getOrEmpty("Authorization").get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    token = authHeader.substring(7);
                    username = jwtUtil.extractUsername(token);
                    role = jwtUtil.extractRole(token);

                    modifiedExchange = exchange.mutate()
                            .request(exchange.getRequest().mutate()
                                    .header("username", username).build())
                            .build();
                }

                if (jwtUtil.isTokenExpired(token)) {
                    throw new UnauthorizedException("Unauthorized");
                }

                routeValidator.validateRoute(exchange, role);
                return chain.filter(modifiedExchange);
            }

            return chain.filter(exchange);

        });
    }
}