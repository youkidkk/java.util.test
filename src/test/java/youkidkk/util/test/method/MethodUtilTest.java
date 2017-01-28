package youkidkk.util.test.method;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Mockito.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import youkidkk.util.test.ClassForTest;
import youkidkk.util.test.field.FieldUtil;
import youkidkk.util.test.rule.LoggingRule;

import java.lang.reflect.Constructor;
import java.util.Arrays;

/**
 * {@link MethodUtil}のためのテストクラス
 */
@RunWith(PowerMockRunner.class)
public class MethodUtilTest {

    /** ロガー */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /** ルール : 予期された例外 */
    public ExpectedException thrown = ExpectedException.none();

    /** ルールチェーン */
    @Rule
    public RuleChain ruleChain = RuleChain
            .outerRule(new LoggingRule(this.logger))
            .around(this.thrown);

    /**
     * コンストラクタのテスト
     *
     * @throws Exception 予期せぬ例外
     */
    @Test
    public void testMethodUtil() throws Exception {
        Constructor<MethodUtil> constructor = MethodUtil.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        MethodUtil instance = constructor.newInstance();
        assertThat(instance, instanceOf(MethodUtil.class));
    }

    /**
     * TestUtil#invokePrivateConstructor のテストメソッド
     * {@link MethodUtil#invokePrivateConstructor(Class)}
     * @throws Exception 予期せぬ例外
     */
    @Test
    public void testInvokePrivateConstructorWithNoArgs() throws Exception {
        ClassForTest instance = MethodUtil.invokePrivateConstructor(ClassForTest.class);
        assertThat(instance, instanceOf(ClassForTest.class));
    }

    /**
     * TestUtil#invokePrivateConstructor のテストメソッド
     * {@link MethodUtil#invokePrivateConstructor(Class, java.util.List, java.util.List)}
     * @throws Exception 予期せぬ例外
     */
    @Test
    public void testInvokePrivateConstructor() throws Exception {
        final int testIntValue = 1;
        final String testStringValue = "abc";

        ClassForTest instance = MethodUtil.invokePrivateConstructor(ClassForTest.class,
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
     * TestUtil#invokePrivateMethod のテストメソッド
     * {@link MethodUtil#invokePrivateMethod(Class, Object, String)}
     *
     * @throws Exception 予期せぬ例外
     */
    @PrepareForTest(ClassForTest.class)
    @Test
    public void testInvokePrivateMethodWithNoArgs() throws Exception {
        ClassForTest instance = new ClassForTest(1);
        String result = MethodUtil.invokePrivateMethod(ClassForTest.class, instance,
                "privateMethod");
        assertThat(result, is("result : none"));
    }

    /**
     * TestUtil#invokePrivateMethod のテストメソッド
     * {@link MethodUtil#invokePrivateMethod(Class, Object, String, java.util.List, java.util.List)}
     *
     * @throws Exception 予期せぬ例外
     */
    @PrepareForTest(ClassForTest.class)
    @Test
    public void testInvokePrivateMethod() throws Exception {
        ClassForTest instance = new ClassForTest(1);
        String result = MethodUtil.invokePrivateMethod(ClassForTest.class, instance,
                "privateMethod",
                Arrays.asList(123, "test string"), Arrays.asList(int.class, String.class));
        assertThat(result, is("result : 123 : test string"));
    }

    /**
     * TestUtil#invokePrivateVoidMethod のテストメソッド
     * {@link MethodUtil#invokePrivateVoidMethod(Class, Object, String)}
     *
     * @throws Exception 予期せぬ例外
     */
    @PrepareForTest(ClassForTest.class)
    @Test
    public void testInvokePrivateVoidMethodWithNoArgs() throws Exception {
        ClassForTest mock = PowerMockito.mock(ClassForTest.class);
        MethodUtil.invokePrivateVoidMethod(ClassForTest.class, mock, "privateVoidMethod");
        PowerMockito.verifyPrivate(mock, times(1)).invoke("privateVoidMethod");
    }

    /**
     * TestUtil#invokePrivateVoidMethod のテストメソッド
     * {@link MethodUtil#invokePrivateVoidMethod(Class, Object, String, java.util.List, java.util.List)}
     *
     * @throws Exception 予期せぬ例外
     */
    @PrepareForTest(ClassForTest.class)
    @Test
    public void testInvokePrivateVoidMethod() throws Exception {
        ClassForTest mock = PowerMockito.mock(ClassForTest.class);
        MethodUtil.invokePrivateVoidMethod(ClassForTest.class, mock,
                "privateVoidMethod",
                Arrays.asList(123, "test string"), Arrays.asList(int.class, String.class));
        PowerMockito.verifyPrivate(mock, times(1)).invoke("privateVoidMethod", 123,
                "test string");
    }

    /**
     * TestUtil#invokePrivateStaticMethod のテストメソッド
     * {@link MethodUtil#invokePrivateStaticMethod(Class, String)}
     *
     * @throws Exception 予期せぬ例外
     */
    @PrepareForTest(ClassForTest.class)
    @Test
    public void testInvokePrivateStaticMethodWithNoArgs() throws Exception {
        String result = MethodUtil.invokePrivateStaticMethod(ClassForTest.class,
                "privateStaticMethod");
        assertThat(result, is("result : static none"));
    }

    /**
     * TestUtil#invokePrivateStaticMethod のテストメソッド
     * {@link MethodUtil#invokePrivateStaticMethod(Class, String, java.util.List, java.util.List)}
     *
     * @throws Exception 予期せぬ例外
     */
    @PrepareForTest(ClassForTest.class)
    @Test
    public void testInvokePrivateStaticMethod() throws Exception {
        String result = MethodUtil.invokePrivateStaticMethod(ClassForTest.class,
                "privateStaticMethod",
                Arrays.asList("test string", 123), Arrays.asList(String.class, int.class));
        assertThat(result, is("result : test string : 123"));
    }

    /**
     * TestUtil#invokePrivateStaticVoidMethod のテストメソッド
     * {@link MethodUtil#invokePrivateStaticVoidMethod(Class, String)}
     * @throws Exception 予期せぬ例外
     */
    @PrepareForTest(ClassForTest.class)
    @Test
    public void testInvokePrivateStaticVoidMethodWithNoArgs() throws Exception {
        PowerMockito.mockStatic(ClassForTest.class);
        MethodUtil.invokePrivateStaticVoidMethod(ClassForTest.class,
                "privateStaticVoidMethod");
        PowerMockito.verifyPrivate(ClassForTest.class, times(1))
                .invoke("privateStaticVoidMethod");
    }

    /**
     * TestUtil#invokePrivateStaticVoidMethod のテストメソッド
     * {@link MethodUtil#invokePrivateStaticVoidMethod(Class, String, java.util.List, java.util.List)}
     * @throws Exception 予期せぬ例外
     */
    @PrepareForTest(ClassForTest.class)
    @Test
    public void testInvokePrivateStaticVoidMethod() throws Exception {
        PowerMockito.mockStatic(ClassForTest.class);
        MethodUtil.invokePrivateStaticVoidMethod(ClassForTest.class,
                "privateStaticVoidMethod",
                Arrays.asList("test string", 123), Arrays.asList(String.class, int.class));
        PowerMockito.verifyPrivate(ClassForTest.class, times(1))
                .invoke("privateStaticVoidMethod", "test string", 123);
    }

}
