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
     * テスト用メソッド
     *
     * @param i 引数（int）
     * @param s 引数（String）
     * @return String
     */
    @SuppressWarnings("unused")
    private String privateMethod(int i, String s) {
        return "result : " + i + " : " + s;
    }

    /**
     * TestUtil#invokePrivateMethod のテストメソッド
     * {@link TestUtil#invokePrivateMethod(Class, Object, String, java.util.List, java.util.List)}
     *
     * @throws Exception 予期せぬ例外
     */
    @PrepareForTest(TestUtilTest.class)
    @Test
    public void testInvokePrivateMethod() throws Exception {
        TestUtilTest instance = new TestUtilTest();
        String result = TestUtil.invokePrivateMethod(TestUtilTest.class, instance,
                "privateMethod",
                Arrays.asList(123, "test string"), Arrays.asList(int.class, String.class));
        assertThat(result, is("result : 123 : test string"));
    }

    /**
     * テスト用メソッド
     *
     * @param i 引数（int）
     * @param s 引数（String）
     */
    @SuppressWarnings("unused")
    private void privateVoidMethod(int i, String s) {
    }

    /**
     * TestUtil#invokePrivateVoidMethod のテストメソッド
     * {@link TestUtil#invokePrivateVoidMethod(Class, Object, String, java.util.List, java.util.List)}
     *
     * @throws Exception 予期せぬ例外
     */
    @PrepareForTest(TestUtilTest.class)
    @Test
    public void testInvokePrivateVoidMethod() throws Exception {
        TestUtilTest mock = PowerMockito.mock(TestUtilTest.class);
        TestUtil.invokePrivateVoidMethod(TestUtilTest.class, mock,
                "privateVoidMethod",
                Arrays.asList(123, "test string"), Arrays.asList(int.class, String.class));
        PowerMockito.verifyPrivate(mock, times(1)).invoke("privateVoidMethod", 123,
                "test string");
    }

    /**
     * テスト用メソッド
     *
     * @param s 引数（String）
     * @param i 引数（int）
     * @return String
     */
    @SuppressWarnings("unused")
    private static String privateStaticMethod(String s, int i) {
        return "result : " + s + " : " + i;
    }

    /**
     * TestUtil#invokePrivateStaticMethod のテストメソッド
     * {@link TestUtil#invokePrivateStaticMethod(Class, String, java.util.List, java.util.List)}
     *
     * @throws Exception 予期せぬ例外
     */
    @PrepareForTest(TestUtilTest.class)
    @Test
    public void testInvokePrivateStaticMethod() throws Exception {
        String result = TestUtil.invokePrivateStaticMethod(TestUtilTest.class,
                "privateStaticMethod",
                Arrays.asList("test string", 123), Arrays.asList(String.class, int.class));
        assertThat(result, is("result : test string : 123"));
    }

    /**
     * テスト用メソッド
     *
     * @param s 引数（String）
     * @param i 引数（int）
     */
    @SuppressWarnings("unused")
    private static void privateStaticVoidMethod(String s, int i) {
    }

    /**
     * TestUtil#invokePrivateStaticVoidMethod のテストメソッド
     * {@link TestUtil#invokePrivateStaticVoidMethod(Class, String, java.util.List, java.util.List)}
     * @throws Exception 予期せぬ例外
     */
    @PrepareForTest(TestUtilTest.class)
    @Test
    public void testInvokePrivateStaticVoidMethod() throws Exception {
        PowerMockito.mockStatic(TestUtilTest.class);
        TestUtil.invokePrivateStaticVoidMethod(TestUtilTest.class,
                "privateStaticVoidMethod",
                Arrays.asList("test string", 123), Arrays.asList(String.class, int.class));
        PowerMockito.verifyPrivate(TestUtilTest.class, times(1))
                .invoke("privateStaticVoidMethod", "test string", 123);
    }

    /** テスト用private変数 */
    @SuppressWarnings("unused")
    private int privateIntField = 123;

    /** テスト用private変数 */
    @SuppressWarnings("unused")
    private String privateStringField = "abc";

    /**
     * TestUtil#getPrivateFieldValue のテストメソッド
     * {@link TestUtil#getPrivateFieldValue(Class, Object, String)}
     * @throws Exception 予期せぬ例外
     */
    @PrepareForTest(TestUtilTest.class)
    @Test
    public void testGetPrivateFieldValue() throws Exception {
        int privateIntFieldValue = TestUtil.getPrivateFieldValue(
                this.getClass(), this, "privateIntField");
        assertThat(privateIntFieldValue, is(123));

        String privateStringFieldValue = TestUtil.getPrivateFieldValue(
                this.getClass(), this, "privateStringField");
        assertThat(privateStringFieldValue, is("abc"));
    }

    /** テスト用private変数 */
    @SuppressWarnings("unused")
    private static int privateStaticIntField = 456;

    /** テスト用private変数 */
    @SuppressWarnings("unused")
    private static String privateStaticStringField = "def";

    /**
     * TestUtil#getPrivateStaticFieldValue のテストメソッド
     * {@link TestUtil#getPrivateStaticFieldValue(Class, String)}
     * @throws Exception 予期せぬ例外
     */
    @PrepareForTest(TestUtilTest.class)
    @Test
    public void testGetPrivateStaticFieldValue() throws Exception {
        int privateIntFieldValue = TestUtil.getPrivateStaticFieldValue(
                this.getClass(), "privateStaticIntField");
        assertThat(privateIntFieldValue, is(456));

        String privateStringFieldValue = TestUtil.getPrivateStaticFieldValue(
                this.getClass(), "privateStaticStringField");
        assertThat(privateStringFieldValue, is("def"));
    }

}
