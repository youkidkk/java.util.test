package youkidkk.util.test;

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
import youkidkk.util.test.rule.LoggingRule;

import java.lang.reflect.Constructor;
import java.util.Arrays;

/**
 * テスト用ユーティリティクラス テストクラス
 */
@RunWith(PowerMockRunner.class)
public class TestUtilTest {

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
    public void testTestUtil() throws Exception {
        Constructor<TestUtil> constructor = TestUtil.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        TestUtil instance = constructor.newInstance();
        assertThat(instance, instanceOf(TestUtil.class));
    }

    /**
     * TestUtil#invokePrivateConstructor のテストメソッド
     * {@link TestUtil#invokePrivateConstructor(Class)}
     * @throws Exception 予期せぬ例外
     */
    @Test
    public void testInvokePrivateConstructorWithNoArgs() throws Exception {
        ClassForTest instance = TestUtil.invokePrivateConstructor(ClassForTest.class);
        assertThat(instance, instanceOf(ClassForTest.class));
    }

    /**
     * TestUtil#invokePrivateConstructor のテストメソッド
     * {@link TestUtil#invokePrivateConstructor(Class, java.util.List, java.util.List)}
     * @throws Exception 予期せぬ例外
     */
    @Test
    public void testInvokePrivateConstructor() throws Exception {
        final int testIntValue = 1;
        final String testStringValue = "abc";

        ClassForTest instance = TestUtil.invokePrivateConstructor(ClassForTest.class,
                Arrays.asList(testIntValue, testStringValue),
                Arrays.asList(int.class, String.class));
        int intFieldValue = TestUtil.getPrivateFieldValue(ClassForTest.class, instance, "intField");
        assertThat(intFieldValue, is(testIntValue));
        String stringFieldValue = TestUtil.getPrivateFieldValue(ClassForTest.class, instance,
                "stringField");
        assertThat(stringFieldValue, is(testStringValue));
    }

    /**
     * TestUtil#invokePrivateMethod のテストメソッド
     * {@link TestUtil#invokePrivateMethod(Class, Object, String)}
     *
     * @throws Exception 予期せぬ例外
     */
    @PrepareForTest(ClassForTest.class)
    @Test
    public void testInvokePrivateMethodWithNoArgs() throws Exception {
        ClassForTest instance = new ClassForTest(1);
        String result = TestUtil.invokePrivateMethod(ClassForTest.class, instance,
                "privateMethod");
        assertThat(result, is("result : none"));
    }

    /**
     * TestUtil#invokePrivateMethod のテストメソッド
     * {@link TestUtil#invokePrivateMethod(Class, Object, String, java.util.List, java.util.List)}
     *
     * @throws Exception 予期せぬ例外
     */
    @PrepareForTest(ClassForTest.class)
    @Test
    public void testInvokePrivateMethod() throws Exception {
        ClassForTest instance = new ClassForTest(1);
        String result = TestUtil.invokePrivateMethod(ClassForTest.class, instance,
                "privateMethod",
                Arrays.asList(123, "test string"), Arrays.asList(int.class, String.class));
        assertThat(result, is("result : 123 : test string"));
    }

    /**
     * TestUtil#invokePrivateVoidMethod のテストメソッド
     * {@link TestUtil#invokePrivateVoidMethod(Class, Object, String)}
     *
     * @throws Exception 予期せぬ例外
     */
    @PrepareForTest(ClassForTest.class)
    @Test
    public void testInvokePrivateVoidMethodWithNoArgs() throws Exception {
        ClassForTest mock = PowerMockito.mock(ClassForTest.class);
        TestUtil.invokePrivateVoidMethod(ClassForTest.class, mock, "privateVoidMethod");
        PowerMockito.verifyPrivate(mock, times(1)).invoke("privateVoidMethod");
    }

    /**
     * TestUtil#invokePrivateVoidMethod のテストメソッド
     * {@link TestUtil#invokePrivateVoidMethod(Class, Object, String, java.util.List, java.util.List)}
     *
     * @throws Exception 予期せぬ例外
     */
    @PrepareForTest(ClassForTest.class)
    @Test
    public void testInvokePrivateVoidMethod() throws Exception {
        ClassForTest mock = PowerMockito.mock(ClassForTest.class);
        TestUtil.invokePrivateVoidMethod(ClassForTest.class, mock,
                "privateVoidMethod",
                Arrays.asList(123, "test string"), Arrays.asList(int.class, String.class));
        PowerMockito.verifyPrivate(mock, times(1)).invoke("privateVoidMethod", 123,
                "test string");
    }

    /**
     * TestUtil#invokePrivateStaticMethod のテストメソッド
     * {@link TestUtil#invokePrivateStaticMethod(Class, String)}
     *
     * @throws Exception 予期せぬ例外
     */
    @PrepareForTest(ClassForTest.class)
    @Test
    public void testInvokePrivateStaticMethodWithNoArgs() throws Exception {
        String result = TestUtil.invokePrivateStaticMethod(ClassForTest.class,
                "privateStaticMethod");
        assertThat(result, is("result : static none"));
    }

    /**
     * TestUtil#invokePrivateStaticMethod のテストメソッド
     * {@link TestUtil#invokePrivateStaticMethod(Class, String, java.util.List, java.util.List)}
     *
     * @throws Exception 予期せぬ例外
     */
    @PrepareForTest(ClassForTest.class)
    @Test
    public void testInvokePrivateStaticMethod() throws Exception {
        String result = TestUtil.invokePrivateStaticMethod(ClassForTest.class,
                "privateStaticMethod",
                Arrays.asList("test string", 123), Arrays.asList(String.class, int.class));
        assertThat(result, is("result : test string : 123"));
    }

    /**
     * TestUtil#invokePrivateStaticVoidMethod のテストメソッド
     * {@link TestUtil#invokePrivateStaticVoidMethod(Class, String)}
     * @throws Exception 予期せぬ例外
     */
    @PrepareForTest(ClassForTest.class)
    @Test
    public void testInvokePrivateStaticVoidMethodWithNoArgs() throws Exception {
        PowerMockito.mockStatic(ClassForTest.class);
        TestUtil.invokePrivateStaticVoidMethod(ClassForTest.class,
                "privateStaticVoidMethod");
        PowerMockito.verifyPrivate(ClassForTest.class, times(1))
                .invoke("privateStaticVoidMethod");
    }

    /**
     * TestUtil#invokePrivateStaticVoidMethod のテストメソッド
     * {@link TestUtil#invokePrivateStaticVoidMethod(Class, String, java.util.List, java.util.List)}
     * @throws Exception 予期せぬ例外
     */
    @PrepareForTest(ClassForTest.class)
    @Test
    public void testInvokePrivateStaticVoidMethod() throws Exception {
        PowerMockito.mockStatic(ClassForTest.class);
        TestUtil.invokePrivateStaticVoidMethod(ClassForTest.class,
                "privateStaticVoidMethod",
                Arrays.asList("test string", 123), Arrays.asList(String.class, int.class));
        PowerMockito.verifyPrivate(ClassForTest.class, times(1))
                .invoke("privateStaticVoidMethod", "test string", 123);
    }

    /**
     * TestUtil#getPrivateFieldValue のテストメソッド
     * {@link TestUtil#getPrivateFieldValue(Class, Object, String)}
     * @throws Exception 予期せぬ例外
     */
    @PrepareForTest(ClassForTest.class)
    @Test
    public void testGetPrivateFieldValue() throws Exception {
        ClassForTest instance = new ClassForTest(1);
        int privateIntFieldValue = TestUtil.getPrivateFieldValue(
                ClassForTest.class, instance, "privateIntField");
        assertThat(privateIntFieldValue, is(123));

        String privateStringFieldValue = TestUtil.getPrivateFieldValue(
                ClassForTest.class, instance, "privateStringField");
        assertThat(privateStringFieldValue, is("abc"));
    }

    /**
     * TestUtil#getPrivateStaticFieldValue のテストメソッド
     * {@link TestUtil#getPrivateStaticFieldValue(Class, String)}
     * @throws Exception 予期せぬ例外
     */
    @PrepareForTest(ClassForTest.class)
    @Test
    public void testGetPrivateStaticFieldValue() throws Exception {
        int privateIntFieldValue = TestUtil.getPrivateStaticFieldValue(
                ClassForTest.class, "privateStaticIntField");
        assertThat(privateIntFieldValue, is(456));

        String privateStringFieldValue = TestUtil.getPrivateStaticFieldValue(
                ClassForTest.class, "privateStaticStringField");
        assertThat(privateStringFieldValue, is("def"));
    }

}
