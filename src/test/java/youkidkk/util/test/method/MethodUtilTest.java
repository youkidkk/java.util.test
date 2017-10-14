package youkidkk.util.test.method;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import youkidkk.util.test.ClassForTest;
import youkidkk.util.test.TestTool;
import youkidkk.util.test.field.FieldUtil;

import java.util.Arrays;
import java.util.List;

/**
 * {@link MethodUtil}のためのテストクラス
 */
public class MethodUtilTest {

    /** ルール : 予期された例外 */
    public ExpectedException thrown = ExpectedException.none();

    /**
     * コンストラクタのテスト
     *
     * @throws Exception 予期せぬ例外
     */
    @Test
    public void testMethodUtil() throws Exception {
        TestTool.testPrivateConstructor(MethodUtil.class);
    }

    /**
     * TestUtil#invokePrivateConstructorWithNoArgs のテストメソッド
     * {@link MethodUtil#invokePrivateConstructorWithNoArgs(Class)}
     * @throws Exception 予期せぬ例外
     */
    @Test
    public void testInvokePrivateConstructorWithNoArgs() throws Exception {
        ClassForTest instance1 = MethodUtil.invokePrivateConstructor(ClassForTest.class);
        assertThat(instance1, instanceOf(ClassForTest.class));

        ClassForTest instance2 = MethodUtil.invokePrivateConstructor(ClassForTest.class,
                (Object[]) null);
        assertThat(instance2, instanceOf(ClassForTest.class));
    }

    /**
     * TestUtil#invokePrivateConstructorWithArgs のテストメソッド
     * {@link MethodUtil#invokePrivateConstructorWithArgs(Class, java.util.List, java.util.List)}
     * @throws Exception 予期せぬ例外
     */
    @Test
    public void testInvokePrivateConstructorWithArgs() throws Exception {
        final int testIntValue = 1;
        final String testStringValue = "abc";

        ClassForTest instance = MethodUtil.invokePrivateConstructor(ClassForTest.class,
                testIntValue, testStringValue);
        int intFieldValue = FieldUtil.getPrivateFieldValue(ClassForTest.class, instance,
                "intField");
        assertThat(intFieldValue, is(testIntValue));
        String stringFieldValue = FieldUtil.getPrivateFieldValue(ClassForTest.class, instance,
                "stringField");
        assertThat(stringFieldValue, is(testStringValue));
    }

    /**
     * TestUtil#invokePrivateMethodWithNoArgs のテストメソッド
     * {@link MethodUtil#invokePrivateMethodWithNoArgs(Class, Object, String)}
     *
     * @throws Exception 予期せぬ例外
     */
    @Test
    public void testInvokePrivateMethodWithNoArgs() throws Exception {
        ClassForTest instance1 = new ClassForTest(1);
        String result1 = MethodUtil.invokePrivateMethod(instance1, "privateMethod");
        assertThat(result1, is("result : none"));

        ClassForTest instance2 = new ClassForTest(1);
        String result2 = MethodUtil.invokePrivateMethod(instance2, "privateMethod",
                (Object[]) null);
        assertThat(result2, is("result : none"));
    }

    /**
     * TestUtil#invokePrivateMethodWithArgs のテストメソッド
     * {@link MethodUtil#invokePrivateMethodWithArgs(Class, Object, String, java.util.List, java.util.List)}
     *
     * @throws Exception 予期せぬ例外
     */
    @Test
    public void testInvokePrivateMethodWithArgs() throws Exception {
        ClassForTest instance = new ClassForTest(1);
        String result = MethodUtil.invokePrivateMethod(instance,
                "privateMethod",
                123, "test string");
        assertThat(result, is("result : 123 : test string"));
    }

    /**
     * TestUtil#invokePrivateVoidMethodWithNoArgs のテストメソッド
     * {@link MethodUtil#invokePrivateVoidMethodWithNoArgs(Class, Object, String)}
     *
     * @throws Exception 予期せぬ例外
     */
    @Test
    public void testInvokePrivateVoidMethodWithNoArgs() throws Exception {
        ClassForTest instance1 = new ClassForTest(1);
        MethodUtil.invokePrivateVoidMethod(instance1, "privateVoidMethod");
        assertThat(ClassForTest.methodInvoked, is("privateVoidMethod with no args"));

        ClassForTest instance2 = new ClassForTest(1);
        MethodUtil.invokePrivateVoidMethod(instance2, "privateVoidMethod", (Object[]) null);
        assertThat(ClassForTest.methodInvoked, is("privateVoidMethod with no args"));
    }

    /**
     * TestUtil#invokePrivateVoidMethodWithArgs のテストメソッド
     * {@link MethodUtil#invokePrivateVoidMethodWithArgs(Class, Object, String, java.util.List, java.util.List)}
     *
     * @throws Exception 予期せぬ例外
     */
    @Test
    public void testInvokePrivateVoidMethodWithArgs() throws Exception {
        ClassForTest instance = new ClassForTest(1);
        MethodUtil.invokePrivateVoidMethod(instance, "privateVoidMethod",
                123, "test string");
        assertThat(ClassForTest.methodInvoked, is("privateVoidMethod with 123 and test string"));
    }

    /**
     * TestUtil#invokePrivateStaticMethodWithNoArgs のテストメソッド
     * {@link MethodUtil#invokePrivateStaticMethodWithNoArgs(Class, String)}
     *
     * @throws Exception 予期せぬ例外
     */
    @Test
    public void testInvokePrivateStaticMethodWithNoArgs() throws Exception {
        String result1 = MethodUtil.invokePrivateStaticMethod(ClassForTest.class,
                "privateStaticMethod");
        assertThat(result1, is("result : static none"));

        String result2 = MethodUtil.invokePrivateStaticMethod(ClassForTest.class,
                "privateStaticMethod", (Object[]) null);
        assertThat(result2, is("result : static none"));
    }

    /**
     * TestUtil#invokePrivateStaticMethodWithArgs のテストメソッド
     * {@link MethodUtil#invokePrivateStaticMethodWithArgs(Class, String, java.util.List, java.util.List)}
     *
     * @throws Exception 予期せぬ例外
     */
    @Test
    public void testInvokePrivateStaticMethodWithArgs() throws Exception {
        String result = MethodUtil.invokePrivateStaticMethod(ClassForTest.class,
                "privateStaticMethod",
                "test string", 123);
        assertThat(result, is("result : test string : 123"));
    }

    /**
     * TestUtil#invokePrivateStaticVoidMethodWithNoArgs のテストメソッド
     * {@link MethodUtil#invokePrivateStaticVoidMethodWithNoArgs(Class, String)}
     * @throws Exception 予期せぬ例外
     */
    @Test
    public void testInvokePrivateStaticVoidMethodWithNoArgs() throws Exception {
        MethodUtil.invokePrivateStaticVoidMethod(ClassForTest.class,
                "privateStaticVoidMethod");
        assertThat(ClassForTest.methodInvoked, is("privateStaticVoidMethod with no args"));

        MethodUtil.invokePrivateStaticVoidMethod(ClassForTest.class,
                "privateStaticVoidMethod", (Object[]) null);
        assertThat(ClassForTest.methodInvoked, is("privateStaticVoidMethod with no args"));
    }

    /**
     * TestUtil#invokePrivateStaticVoidMethodWithArgs のテストメソッド
     * {@link MethodUtil#invokePrivateStaticVoidMethodWithArgs(Class, String, java.util.List, java.util.List)}
     * @throws Exception 予期せぬ例外
     */
    @Test
    public void testInvokePrivateStaticVoidMethodWithArgs() throws Exception {
        MethodUtil.invokePrivateStaticVoidMethod(ClassForTest.class,
                "privateStaticVoidMethod",
                "test string", 123);
        assertThat(ClassForTest.methodInvoked,
                is("privateStaticVoidMethod with test string and 123"));
    }

    /**
     * TestUtil#mapToPrimitiveClassList のテストメソッド
     *
     * @throws Exception 予期せぬ例外
     */
    @Test
    public void testMapToPrimitiveClassList() throws Exception {
        Object[] args = new Object[] { new Byte((byte) 1), new Short((short) 2), new Integer(3),
                new Long(4L), new Character('a'), new Float(0.5F), new Double(0.6D),
                new Boolean(true), "test" };
        List<Class<?>> result = MethodUtil.invokePrivateStaticMethod(MethodUtil.class,
                "mapToPrimitiveClassList", (Object) args);
        assertThat(result, is(Arrays.asList(byte.class, short.class, int.class, long.class,
                char.class, float.class, double.class, boolean.class, String.class)));
    }
}
