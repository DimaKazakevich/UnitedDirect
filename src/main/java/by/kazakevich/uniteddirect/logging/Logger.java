package by.kazakevich.uniteddirect.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
public class Logger {
    @Pointcut("@annotation(Loggable)")
    public void webServiceMethod() { }

    @Around("webServiceMethod()")
    public Object logWebServiceCall(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        String methodName = thisJoinPoint.getSignature().getName();
        Object[] methodArgs = thisJoinPoint.getArgs();
        log.info("Method: " + methodName + ", args: " + Arrays.toString(methodArgs));
        Object result = thisJoinPoint.proceed();
        log.info("Method " + methodName + " returns " + result);

        return result;
    }

    @Before("webServiceMethod()")
    public void beforeCallAtWebServiceMethod(JoinPoint jp) {
        String args = Arrays.stream(jp.getArgs())
                .map(a -> a.toString())
                .collect(Collectors.joining(","));
        log.info("before " + jp.toString() + ", args=[" + args + "]");
    }

    @After("webServiceMethod()")
    public void afterCallAtWebServiceMethod(JoinPoint jp) {
        log.info("After " + jp.toString());
    }

    @AfterThrowing(pointcut = "webServiceMethod()", throwing = "ex")
    public void logAfterThrowingWebServiceMethod(Exception ex) throws Throwable
    {
        log.info("After Throwing " + ex);
    }
}
