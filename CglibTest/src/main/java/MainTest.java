import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Field;

public class MainTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallback(new MyMethodInterceptor());
        UserServiceImpl userService = (UserServiceImpl)enhancer.create();

        // 直接将value设置到相应的属性域内
        Field declaredField = UserServiceImpl.class.getDeclaredField("name");
        declaredField.setAccessible(true);
        declaredField.set(userService, "你好");

        userService.add();
        userService.delete(1);
    }
}
