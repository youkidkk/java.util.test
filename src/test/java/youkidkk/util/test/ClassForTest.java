package youkidkk.util.test;

/**
 * テスト用クラス
 */
public class ClassForTest {

    /** コンストラクタテスト用フィールド */
    @SuppressWarnings("unused")
    private int intField;
    /** コンストラクタテスト用フィールド */
    @SuppressWarnings("unused")
    private String stringField;

    /**
     * テスト用コンストラクタ
     *
     * @param i 引数（int）
     * @param s 引数（String）
     */
    @SuppressWarnings("unused")
    private ClassForTest(int i, String s) {
        this.intField = i;
        this.stringField = s;
    }

    /**
     * テスト用コンストラクタ
     *
     */
    @SuppressWarnings("unused")
    private ClassForTest() {
    }

    /**
     * テスト用コンストラクタ
     * @param i 引数
     *
     */
    public ClassForTest(int i) {
    }

    /**
     * テスト用メソッド
     *
     * @return String
     */
    @SuppressWarnings("unused")
    private String privateMethod() {
        return "result : none";
    }

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
     * テスト用メソッド
     *
     */
    @SuppressWarnings("unused")
    private void privateVoidMethod() {
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
     * テスト用メソッド
     *
     * @return String
     */
    @SuppressWarnings("unused")
    private static String privateStaticMethod() {
        return "result : static none";
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
     * テスト用メソッド
     *
     */
    @SuppressWarnings("unused")
    private static void privateStaticVoidMethod() {
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

    /** テスト用private変数 */
    @SuppressWarnings("unused")
    private int privateIntField = 123;

    /** テスト用private変数 */
    @SuppressWarnings("unused")
    private String privateStringField = "abc";

    /** テスト用private変数 */
    @SuppressWarnings("unused")
    private static int privateStaticIntField = 456;

    /** テスト用private変数 */
    @SuppressWarnings("unused")
    private static String privateStaticStringField = "def";

}
