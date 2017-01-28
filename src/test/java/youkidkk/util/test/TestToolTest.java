package youkidkk.util.test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * {@link youkidkk.util.test.TestTool} のためのテスト・クラス。
 */
public class TestToolTest {

    /**
     * {@link youkidkk.util.test.TestTool#TestTool()} のためのテスト・メソッド。
     *
     * @throws Exception 例外
     */
    @SuppressWarnings("javadoc")
    @Test
    public void testTestUtil() throws Exception {
        TestTool.testPrivateConstructor(TestTool.class);
    }

    /**
     * {@link youkidkk.util.test.TestTool#testPrivateConstructor(Class)} のためのテスト・メソッド。
     */
    @Test
    public void testTestPrivateConstructor() {
        try {
            TestTool.testPrivateConstructor(ConstructorThrowsException.class);
            fail("例外が発生しない");
        } catch (Exception e) {
            assertThat(e, instanceOf(InvocationTargetException.class));
        }
    }

}
