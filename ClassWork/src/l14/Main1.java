package l14;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main1 {

    public static void main(String[] args) {
        Test test = new Test();

        Class classTest = test.getClass();

        System.out.println(classTest.getSimpleName());

        int mod = classTest.getModifiers();
        if (Modifier.isAbstract(mod)) {
            System.out.println("Abstract");
        } else {
            System.out.println("Not abstract");
        }

        Field[] fields = classTest.getDeclaredFields();
        for (Field f : fields) {
            try {
                f.setAccessible(true);
                System.out.println(f.getName() + " " + f.get(test));
                f.set(test, 50);
                System.out.println(f.getName() + " " + f.get(test));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        Method[] methods = classTest.getDeclaredMethods();
        for (Method m :
                methods) {
            try {
                m.setAccessible(true);
                m.invoke(test);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
