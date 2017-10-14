package youkidkk.util.test.field;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;
import youkidkk.util.test.ClassForTest;
import youkidkk.util.test.TestTool;

/**
 * {@link FieldUtil}のためのテストクラス
 */
public class FieldUtilTest {

    /**
     * コンストラクタのテスト
     *
     * @throws Exception 予期せぬ例外
     */
    @Test
    public void testMethodUtil() throws Exception {
        TestTool.testPrivateConstructor(FieldUtil.class);
    }

    /**
     * TestUtil#getPrivateFieldValue のテストメソッド
     * {@link FieldUtil#getPrivateFieldValue(Class, Object, String)}
     * @throws Exception 予期せぬ例外
     */
    @Test
    public void testGetPrivateFieldValue() throws Exception {
        ClassForTest instance = new ClassForTest(1);
        int privateIntFieldValue = FieldUtil.getPrivateFieldValue(
                instance, "privateIntField");
        assertThat(privateIntFieldValue, is(123));

        String privateStringFieldValue = FieldUtil.getPrivateFieldValue(
                instance, "privateStringField");
        assertThat(privateStringFieldValue, is("abc"));
    }

    /**
     * TestUtil#getPrivateStaticFieldValue のテストメソッド
     * {@link FieldUtil#getPrivateStaticFieldValue(Class, String)}
     * @throws Exception 予期せぬ例外
     */
    @Test
    public void testGetPrivateStaticFieldValue() throws Exception {
        int privateIntFieldValue = FieldUtil.getPrivateStaticFieldValue(
                ClassForTest.class, "privateStaticIntField");
        assertThat(privateIntFieldValue, is(456));

        String privateStringFieldValue = FieldUtil.getPrivateStaticFieldValue(
                ClassForTest.class, "privateStaticStringField");
        assertThat(privateStringFieldValue, is("def"));
    }

}
