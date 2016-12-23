package youkidkk.util.test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * テスト用ユーティリティクラス
 */
public class TestUtil {

    /**
     * コンストラクタ（呼び出し不可）。
     *
     */
    private TestUtil() {
    }

    /**
     * privateメソッドを呼び出す。
     *
     * @param <T> 戻り値の型
     * @param targetClass 呼び出し対象クラス
     * @param targetObject 呼び出し対象オブジェクト
     * @param targetMethodName 呼び出し対象メソッド名
     * @param args 引数リスト
     * @param argClasses 引数の型リスト
     * @return 実行したメソッドの戻り値
     * @throws SecurityException セキュリティ・マネージャの例外
     * @throws NoSuchMethodException 対象のメソッドが見つからない場合
     * @throws InvocationTargetException 基本となるメソッドが例外をスローする場合
     * @throws IllegalArgumentException メソッド引数異常の場合
     * @throws IllegalAccessException メソッドアクセス異常の場合
     */
    @SuppressWarnings("unchecked")
    public static <T> T invokePrivateMethod(
            Class<?> targetClass,
            Object targetObject,
            String targetMethodName,
            List<Object> args,
            List<Class<?>> argClasses)
            throws NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method method = targetClass.getDeclaredMethod(
                targetMethodName, argClasses.toArray(new Class[0]));
        method.setAccessible(true);
        return (T) method.invoke(targetObject, args.toArray(new Object[0]));
    }

    /**
     * 戻り値のないprivateメソッドを呼び出す。
     *
     * @param targetClass 呼び出し対象クラス
     * @param targetObject 呼び出し対象オブジェクト
     * @param targetMethodName 呼び出し対象メソッド名
     * @param args 引数リスト
     * @param argClasses 引数の型リスト
     * @throws SecurityException セキュリティ・マネージャの例外
     * @throws NoSuchMethodException 対象のメソッドが見つからない場合
     * @throws InvocationTargetException 基本となるメソッドが例外をスローする場合
     * @throws IllegalArgumentException メソッド引数異常の場合
     * @throws IllegalAccessException メソッドアクセス異常の場合
     */
    public static void invokePrivateVoidMethod(
            Class<?> targetClass,
            Object targetObject,
            String targetMethodName,
            List<Object> args,
            List<Class<?>> argClasses)
            throws NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method method = targetClass.getDeclaredMethod(
                targetMethodName, argClasses.toArray(new Class[0]));
        method.setAccessible(true);
        method.invoke(targetObject, args.toArray(new Object[0]));
    }

    /**
     * private-staticメソッドを呼び出す。
     *
     * @param <T> 戻り値の型
     * @param targetClass 呼び出し対象クラス
     * @param targetMethodName 呼び出し対象メソッド名
     * @param args 引数リスト
     * @param argClasses 引数の型リスト
     * @return 実行したメソッドの戻り値
     * @throws SecurityException セキュリティ・マネージャの例外
     * @throws NoSuchMethodException 対象のメソッドが見つからない場合
     * @throws InvocationTargetException 基本となるメソッドが例外をスローする場合
     * @throws IllegalArgumentException メソッド引数異常の場合
     * @throws IllegalAccessException メソッドアクセス異常の場合
     */
    @SuppressWarnings("unchecked")
    public static <T> T invokePrivateStaticMethod(
            Class<?> targetClass,
            String targetMethodName,
            List<Object> args,
            List<Class<?>> argClasses)
            throws NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method method = targetClass.getDeclaredMethod(
                targetMethodName, argClasses.toArray(new Class[0]));
        method.setAccessible(true);
        return (T) method.invoke(targetClass, args.toArray(new Object[0]));
    }

    /**
     * 戻り値のないprivate-staticメソッドを呼び出す。
     *
     * @param targetClass 呼び出し対象クラス
     * @param targetMethodName 呼び出し対象メソッド名
     * @param args 引数リスト
     * @param argClasses 引数の型リスト
     * @throws SecurityException セキュリティ・マネージャの例外
     * @throws NoSuchMethodException 対象のメソッドが見つからない場合
     * @throws InvocationTargetException 基本となるメソッドが例外をスローする場合
     * @throws IllegalArgumentException メソッド引数異常の場合
     * @throws IllegalAccessException メソッドアクセス異常の場合
     */
    public static void invokePrivateStaticVoidMethod(
            Class<?> targetClass,
            String targetMethodName,
            List<Object> args,
            List<Class<?>> argClasses)
            throws NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method method = targetClass.getDeclaredMethod(
                targetMethodName, argClasses.toArray(new Class[0]));
        method.setAccessible(true);
        method.invoke(targetClass, args.toArray(new Object[0]));
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
