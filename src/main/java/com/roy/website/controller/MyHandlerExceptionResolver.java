package com.roy.website.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Author: Roy
 * @Date: 2019/4/28 10:54
 */
@Component
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        printWrite(this.getClass().getSimpleName() + " : " + e.getMessage(), httpServletResponse);
        return new ModelAndView();
    }

    private void printWrite(String s, HttpServletResponse httpServletResponse) {
        try(PrintWriter pw = httpServletResponse.getWriter()){
            pw.write(s);
            pw.flush();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
