package youkidkk.util.test.method;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import youkidkk.util.test.ClassForTest;
import youkidkk.util.test.TestTool;
import youkidkk.util.test.field.FieldUtil;

import java.util.Arrays;

/**
 * {@link MethodUtil}のためのテストクラス
 */
@RunWith(PowerMockRunner.class)
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
        ClassForTest instance = MethodUtil.invokePrivateConstructorWithNoArgs(ClassForTest.class);
        assertThat(instance, instanceOf(ClassForTest.class));
    }

    /**
     * TestUtil#invokePrivateConstructorWithArgs のテストメソッド
     * {@link MethodUtil#invokePrivateConstructorWithArgs(Class, java.util.List, java.util.List)}
     * @throws Exception 予期せぬ例外
     */
    @Test
    public void testInvokePrivateConstructor() throws Exception {
        final int testIntValue = 1;
        final String testStringValue = "abc";

        ClassForTest instance = MethodUtil.invokePrivateConstructorWithArgs(ClassForTest.class,
                Arrays.asList(testIntValue, testStringValue),
                Arrays.asList(int.class, String.class));
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
    @PrepareForTest(ClassForTest.class)
    @Test
    public void testInvokePrivateMethodWithNoArgs() throws Exception {
        ClassForTest instance = new ClassForTest(1);
        String result = MethodUtil.invokePrivateMethodWithNoArgs(ClassForTest.class, instance,
                "privateMethod");
        assertThat(result, is("result : none"));
    }

    /**
     * TestUtil#invokePrivateMethodWithArgs のテストメソッド
     * {@link MethodUtil#invokePrivateMethodWithArgs(Class, Object, String, java.util.List, java.util.List)}
     *
     * @throws Exception 予期せぬ例外
     */
    @PrepareForTest(ClassForTest.class)
    @Test
    public void testInvokePrivateMethod() throws Exception {
        ClassForTest instance = new ClassForTest(1);
        String result = MethodUtil.invokePrivateMethodWithArgs(ClassForTest.class, instance,
                "privateMethod",
                Arrays.asList(123, "test string"), Arrays.asList(int.class, String.class));
        assertThat(result, is("result : 123 : test string"));
    }

    /**
     * TestUtil#invokePrivateVoidMethodWithNoArgs のテストメソッド
     * {@link MethodUtil#invokePrivateVoidMethodWithNoArgs(Class, Object, String)}
     *
     * @throws Exception 予期せぬ例外
     */
    @PrepareForTest(ClassForTest.class)
    @Test
    public void testInvokePrivateVoidMethodWithNoArgs() throws Exception {
        ClassForTest mock = PowerMockito.mock(ClassForTest.class);
        MethodUtil.invokePrivateVoidMethodWithNoArgs(ClassForTest.class, mock, "privateVoidMethod");
        PowerMockito.verifyPrivate(mock, times(1)).invoke("privateVoidMethod");
    }

    /**
     * TestUtil#invokePrivateVoidMethodWithArgs のテストメソッド
     * {@link MethodUtil#invokePrivateVoidMethodWithArgs(Class, Object, String, java.util.List, java.util.List)}
     *
     * @throws Exception 予期せぬ例外
     */
    @PrepareForTest(ClassForTest.class)
    @Test
    public void testInvokePrivateVoidMethod() throws Exception {
        ClassForTest mock = PowerMockito.mock(ClassForTest.class);
        MethodUtil.invokePrivateVoidMethodWithArgs(ClassForTest.class, mock,
                "privateVoidMethod",
                Arrays.asList(123, "test string"), Arrays.asList(int.class, String.class));
        PowerMockito.verifyPrivate(mock, times(1)).invoke("privateVoidMethod", 123,
                "test string");
    }

    /**
     * TestUtil#invokePrivateStaticMethodWithNoArgs のテストメソッド
     * {@link MethodUtil#invokePrivateStaticMethodWithNoArgs(Class, String)}
     *
     * @throws Exception 予期せぬ例外
     */
    @PrepareForTest(ClassForTest.class)
    @Test
    public void testInvokePrivateStaticMethodWithNoArgs() throws Exception {
        String result = MethodUtil.invokePrivateStaticMethodWithNoArgs(ClassForTest.class,
                "privateStaticMethod");
        assertThat(result, is("result : static none"));
    }

    /**
     * TestUtil#invokePrivateStaticMethodWithArgs のテストメソッド
     * {@link MethodUtil#invokePrivateStaticMethodWithArgs(Class, String, java.util.List, java.util.List)}
     *
     * @throws Exception 予期せぬ例外
     */
    @PrepareForTest(ClassForTest.class)
    @Test
    public void testInvokePrivateStaticMethod() throws Exception {
        String result = MethodUtil.invokePrivateStaticMethodWithArgs(ClassForTest.class,
                "privateStaticMethod",
                Arrays.asList("test string", 123), Arrays.asList(String.class, int.class));
        assertThat(result, is("result : test string : 123"));
    }

    /**
     * TestUtil#invokePrivateStaticVoidMethodWithNoArgs のテストメソッド
     * {@link MethodUtil#invokePrivateStaticVoidMethodWithNoArgs(Class, String)}
     * @throws Exception 予期せぬ例外
     */
    @PrepareForTest(ClassForTest.class)
    @Test
    public void testInvokePrivateStaticVoidMethodWithNoArgs() throws Exception {
        PowerMockito.mockStatic(ClassForTest.class);
        MethodUtil.invokePrivateStaticVoidMethodWithNoArgs(ClassForTest.class,
                "privateStaticVoidMethod");
        PowerMockito.verifyPrivate(ClassForTest.class, times(1))
                .invoke("privateStaticVoidMethod");
    }

    /**
     * TestUtil#invokePrivateStaticVoidMethodWithArgs のテストメソッド
     * {@link MethodUtil#invokePrivateStaticVoidMethodWithArgs(Class, String, java.util.List, java.util.List)}
     * @throws Exception 予期せぬ例外
     */
    @PrepareForTest(ClassForTest.class)
    @Test
    public void testInvokePrivateStaticVoidMethod() throws Exception {
        PowerMockito.mockStatic(ClassForTest.class);
        MethodUtil.invokePrivateStaticVoidMethodWithArgs(ClassForTest.class,
                "privateStaticVoidMethod",
                Arrays.asList("test string", 123), Arrays.asList(String.class, int.class));
        PowerMockito.verifyPrivate(ClassForTest.class, times(1))
                .invoke("privateStaticVoidMethod", "test string", 123);
    }

}
