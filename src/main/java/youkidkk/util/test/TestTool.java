package youkidkk.util.test;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

/**
 * テスト用ツールクラス
 */
public class TestTool {

    /**
     * コンストラクタ（呼び出し不可）。
     */
    private TestTool() {
    }

    /**
     * プライベートコンストラクタのテストを行う
     *
     * @param clazz 対象クラス
     * @throws Exception 例外時
     */
    public static void testPrivateConstructor(Class<?> clazz) throws Exception {
        // コンストラクタを取得
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        // コンストラクタが一つだけ存在すること
        MatcherAssert.assertThat(constructors.length, CoreMatchers.is(1));

        // デフォルトコンストラクタが引数なしで private であること
        Constructor<?> defaultConstructor = constructors[0];
        MatcherAssert.assertThat(defaultConstructor.getParameterTypes().length, CoreMatchers.is(0));
        MatcherAssert.assertThat(Modifier.isPrivate(defaultConstructor.getModifiers()),
                CoreMatchers.is(true));

        defaultConstructor.setAccessible(true);
        // コンストラクタを呼び出し、結果をチェック
        Object instance = defaultConstructor.newInstance();
        MatcherAssert.assertThat(instance, CoreMatchers.is(CoreMatchers.notNullValue()));
        MatcherAssert.assertThat(instance, CoreMatchers.instanceOf(clazz));
    }
}
