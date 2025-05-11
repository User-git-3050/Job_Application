package az.msjobgateway.util;

import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

@Component
public class PathMatcher {

    private static final AntPathMatcher matcher = new AntPathMatcher();

    public Boolean match(String path, String pattern){
        return matcher.match(pattern, path);
    }
}
