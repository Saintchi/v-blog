package top.lpepsi.vblog.jwt;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import top.lpepsi.vblog.dto.Response;
import top.lpepsi.vblog.utils.ResponseUtil;
import top.lpepsi.vblog.vdo.ResultCode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: v-blog
 * @description: 权限不足处理器
 * @author: 林北
 * @create: 2020-02-17 10:30
 **/
@Component
public class ResponseAccessDeniedHandler implements AccessDeniedHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseAccessDeniedHandler.class);
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response = ResponseUtil.set(response);
        String json = JSON.toJSONString(Response.customize(ResultCode.ACCESS_DENY, "未登录，请先登录"));
        LOGGER.info("json: "+json);
        response.getWriter().write(json);
    }
}
