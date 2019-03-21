import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyMethodInterceptor implements MethodInterceptor {
    public Object intercept(Object obj, Method method, Object[] arg, MethodProxy proxy) throws Throwable {
        Object object = null;
        if(method.getName().equals("add")){
            System.out.println("Before:" + method);
            object = proxy.invokeSuper(obj, arg);
            System.out.println("After:" + method);
        }
        else {
            object = proxy.invokeSuper(obj, arg);
        }

        return object;
    }
}
