package youkidkk.util.test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;

/**
 *
 */
@RunWith(PowerMockRunner.class)
public class TestUtilTest {

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
     *
     * {@link TestUtil#invokePrivateMethod(Class, Object, String, java.util.List, java.util.List)}
     */
    @PrepareForTest(TestUtilTest.class)
    @Test
    public void testInvokePrivateMethod() {
        try {
            TestUtilTest instance = new TestUtilTest();
            String result = TestUtil.invokePrivateMethod(TestUtilTest.class, instance,
                    "privateMethod",
                    Arrays.asList(123, "test string"), Arrays.asList(int.class, String.class));
            assertThat(result, is("result : 123 : test string"));
        } catch (Throwable t) {
            fail("予期せぬ例外発生 : " + t);
        }
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
     */
    @PrepareForTest(TestUtilTest.class)
    @Test
    public void testInvokePrivateVoidMethod() {
        try {
            TestUtilTest mock = PowerMockito.mock(TestUtilTest.class);
            TestUtil.invokePrivateVoidMethod(TestUtilTest.class, mock,
                    "privateVoidMethod",
                    Arrays.asList(123, "test string"), Arrays.asList(int.class, String.class));
            PowerMockito.verifyPrivate(mock, times(1)).invoke("privateVoidMethod", 123,
                    "test string");
        } catch (Throwable t) {
            fail("予期せぬ例外発生 : " + t);
        }
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
     */
    @PrepareForTest(TestUtilTest.class)
    @Test
    public void testInvokePrivateStaticMethod() {
        try {
            String result = TestUtil.invokePrivateStaticMethod(TestUtilTest.class,
                    "privateStaticMethod",
                    Arrays.asList("test string", 123), Arrays.asList(String.class, int.class));
            assertThat(result, is("result : test string : 123"));
        } catch (Throwable t) {
            fail("予期せぬ例外発生 : " + t);
        }
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
     */
    @PrepareForTest(TestUtilTest.class)
    @Test
    public void testInvokePrivateStaticVoidMethod() {
        try {
            PowerMockito.mockStatic(TestUtilTest.class);
            TestUtil.invokePrivateStaticVoidMethod(TestUtilTest.class,
                    "privateStaticVoidMethod",
                    Arrays.asList("test string", 123), Arrays.asList(String.class, int.class));
            PowerMockito.verifyPrivate(TestUtilTest.class, times(1))
                    .invoke("privateStaticVoidMethod", "test string", 123);
        } catch (Throwable t) {
            fail("予期せぬ例外発生 : " + t);
        }
    }

}
