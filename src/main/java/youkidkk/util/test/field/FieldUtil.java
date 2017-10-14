package youkidkk.util.test.field;

import java.lang.reflect.Field;

/**
 * フィールドユーティリティークラス
 */
public class FieldUtil {

    /**
     * コンストラクタ（呼び出し不可）。
     */
    private FieldUtil() {
    }

    /**
     * private変数の値を取得する。
     *
     * @param <T> 戻り値の型
     * @param targetClass 対象クラス
     * @param targetObject 対象オブジェクト
     * @param targetFieldName 対象変数名
     * @return 対象変数の値
     * @throws NoSuchFieldException 対象の変数が見つからない場合
     * @throws SecurityException セキュリティ・マネージャの例外
     * @throws IllegalArgumentException メソッド引数異常の場合
     * @throws IllegalAccessException メソッドアクセス異常の場合
     */
    @SuppressWarnings("unchecked")
    public static <T> T getPrivateFieldValue(
            Class<?> targetClass,
            Object targetObject,
            String targetFieldName) throws NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        Field field = targetClass.getDeclaredField(targetFieldName);
        field.setAccessible(true);
        return (T) field.get(targetObject);
    }

    /**
     * private変数の値を取得する。
     *
     * @param <T> 戻り値の型
     * @param targetObject 対象オブジェクト
     * @param targetFieldName 対象変数名
     * @return 対象変数の値
     * @throws NoSuchFieldException 対象の変数が見つからない場合
     * @throws SecurityException セキュリティ・マネージャの例外
     * @throws IllegalArgumentException メソッド引数異常の場合
     * @throws IllegalAccessException メソッドアクセス異常の場合
     */
    public static <T> T getPrivateFieldValue(
            Object targetObject,
            String targetFieldName) throws NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        return getPrivateFieldValue(targetObject.getClass(), targetObject, targetFieldName);
    }

    /**
     * private-static変数の値を取得する。
     *
     * @param <T> 戻り値の型
     * @param targetClass 対象クラス
     * @param targetFieldName 対象変数名
     * @return 対象変数の値
     * @throws NoSuchFieldException 対象の変数が見つからない場合
     * @throws SecurityException セキュリティ・マネージャの例外
     * @throws IllegalArgumentException メソッド引数異常の場合
     * @throws IllegalAccessException メソッドアクセス異常の場合
     */
    public static <T> T getPrivateStaticFieldValue(
            Class<?> targetClass,
            String targetFieldName) throws NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        return getPrivateFieldValue(targetClass, null, targetFieldName);
    }

}
