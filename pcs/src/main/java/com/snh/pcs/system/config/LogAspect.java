package com.snh.pcs.system.config;


import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Around("within(com.snh.pcs..*)")
    public Object logging(ProceedingJoinPoint pjp) throws Throwable {

        String params = getRequestParams();

        long startAt = System.currentTimeMillis();

        logger.info("----------> REQUEST : {}({}) = {}", pjp.getSignature().getDeclaringTypeName(),
                pjp.getSignature().getName(), params);

        Object result = pjp.proceed();

        long endAt = System.currentTimeMillis();

        logger.info("----------> RESPONSE : {}({}) = {} ({}ms)", pjp.getSignature().getDeclaringTypeName(),
                pjp.getSignature().getName(), result, endAt-startAt);

        return result;

    }

    // get requset value
    private String getRequestParams() {

        String params = "없음";

        RequestAttributes requestAttributes = RequestContextHolder
            .getRequestAttributes(); // 3

        if (requestAttributes != null) {
          HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
              .getRequestAttributes()).getRequest(); 

          Map<String, String[]> paramMap = request.getParameterMap();
          if (!paramMap.isEmpty()) {
        	  params = " ## [" +paramMapToString(paramMap) + "] ##";
          }
        }

        return params;

   }
    
    private String paramMapToString(Map<String, String[]> paramMap) {
        return paramMap.entrySet().stream().map(entry -> String.format("%s -> (%s)",
                    entry.getKey()+""+entry.getValue()))
                .collect(Collectors.joining(", "));
    }

}