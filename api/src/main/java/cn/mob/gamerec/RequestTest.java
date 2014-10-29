package cn.mob.gamerec;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/10/16
 */
@Component
public class RequestTest extends HttpServletRequestWrapper {
    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request
     * @throws IllegalArgumentException if the request is null
     */
    public RequestTest(HttpServletRequest request) {
        super(request);
    }
}
