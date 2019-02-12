package be.pxl.s2it.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class GetAspect {
    @Before(value = "execution(* be.pxl.s2it.controllers.RestoController .get*(..))")
    public void before() {
        System.out.println("Before executing method get");
    }

    @AfterReturning(value = "execution(* * .getResto(..))")
    public void afterReturning() {
        System.out.println("After returning method get");
    }

    @AfterThrowing(value = "execution(* *.getResto(..))")
    public void afterThrowing() {
        System.out.println("After Exception in get method");
    }

    @After(value = "execution(* *.getResto(..))")
    public void after() {
        System.out.println("After get call");
    }

    @Around(value = "execution(* *.getResto(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("Before proceeding");
        Object returnValue = pjp.proceed();
        System.out.println("After proceeding");
        System.out.println(returnValue);

        return returnValue;

    }
}
