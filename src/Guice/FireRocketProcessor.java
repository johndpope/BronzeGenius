package Guice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author xuch.
 */
public class FireRocketProcessor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        String value = methodInvocation.getMethod().getAnnotation(FireRocket.class).value();
        System.out.println("Firing rocket from " + value);
        return methodInvocation.proceed();
    }
}
