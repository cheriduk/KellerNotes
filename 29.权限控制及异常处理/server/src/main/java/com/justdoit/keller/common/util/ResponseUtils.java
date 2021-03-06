package com.justdoit.keller.common.util;

import com.justdoit.keller.common.response.Response;
import com.justdoit.keller.common.response.ResultData;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

/**
 * 应答解析工具类
 * @author yangkaile
 * @date 2019-10-28 14:16:07
 */
public class ResponseUtils {
    /**
     * 根据组件返回的错误码重组应答报文
     * @param exception
     * @return
     */
    public static ResponseEntity<String> getResponseFromException(Exception exception){
        ResponseEntity response;
        if(exception instanceof HttpClientErrorException){
            HttpClientErrorException errorException = (HttpClientErrorException) exception;
            switch (errorException.getStatusCode()){
                case FORBIDDEN:  response = Response.forbidden(); break;
                case BAD_REQUEST: response = Response.badRequest();break;
                case UNAUTHORIZED: response = Response.unauthorized();break;
                case INTERNAL_SERVER_ERROR:
                default:response = Response.error();break;
            }
        }else {
            response = Response.badRequest();
        }
        return  response;
    }
}
