package youkidkk.util.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * テスト用ユーティリティクラス
 */
public class TestUtil {

    /**
     * privateメソッドを呼び出す。
     *
     * @param targetClass 呼び出し対象クラス
     * @param targetObject 呼び出し対象オブジェクト
     * @param methodName 呼び出し対象メソッド名
     * @param args 引数リスト
     * @param argClasses 引数の型リスト
     * @return メソッドの戻り値
     * @throws SecurityException セキュリティ・マネージャの例外
     * @throws NoSuchMethodException 一致するメソッドが見つからない場合
     * @throws InvocationTargetException 基本となるメソッドが例外をスローする場合
     * @throws IllegalArgumentException メソッド引数異常の場合
     * @throws IllegalAccessException メソッドアクセス異常の場合
     */
    @SuppressWarnings("unchecked")
    public static <T> T invokePrivateMethod(
            Class<?> targetClass,
            Object targetObject,
            String methodName,
            List<Object> args,
            List<Class<?>> argClasses)
            throws NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method method = targetClass.getDeclaredMethod(methodName,
                argClasses.toArray(new Class[0]));
        method.setAccessible(true);
        return (T) method.invoke(targetObject, args.toArray(new Object[0]));
    }

    /**
     * 戻り値のないprivateメソッドを呼び出す。
     *
     * @param targetClass 呼び出し対象クラス
     * @param targetObject 呼び出し対象オブジェクト
     * @param methodName 呼び出し対象メソッド名
     * @param args 引数リスト
     * @param argClasses 引数の型リスト
     * @throws SecurityException セキュリティ・マネージャの例外
     * @throws NoSuchMethodException 一致するメソッドが見つからない場合
     * @throws InvocationTargetException 基本となるメソッドが例外をスローする場合
     * @throws IllegalArgumentException メソッド引数異常の場合
     * @throws IllegalAccessException メソッドアクセス異常の場合
     */
    public static void invokePrivateVoidMethod(
            Class<?> targetClass,
            Object targetObject,
            String methodName,
            List<Object> args,
            List<Class<?>> argClasses)
            throws NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method method = targetClass.getDeclaredMethod(methodName,
                argClasses.toArray(new Class[0]));
        method.setAccessible(true);
        method.invoke(targetObject, args.toArray(new Object[0]));
    }

    /**
     * private-staticメソッドを呼び出す。
     *
     * @param clazz 呼び出し対象クラス
     * @param methodName 呼び出し対象メソッド名
     * @param args 引数リスト
     * @param argClasses 引数の型リスト
     * @return メソッドの戻り値
     * @throws SecurityException セキュリティ・マネージャの例外
     * @throws NoSuchMethodException 一致するメソッドが見つからない場合
     * @throws InvocationTargetException 基本となるメソッドが例外をスローする場合
     * @throws IllegalArgumentException メソッド引数異常の場合
     * @throws IllegalAccessException メソッドアクセス異常の場合
     */
    @SuppressWarnings("unchecked")
    public static <T> T invokePrivateStaticMethod(
            Class<?> clazz,
            String methodName,
            List<Object> args,
            List<Class<?>> argClasses)
            throws NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method method = clazz.getDeclaredMethod(methodName, argClasses.toArray(new Class[0]));
        method.setAccessible(true);
        return (T) method.invoke(clazz, args.toArray(new Object[0]));
    }

    /**
     * 戻り値のないprivate-staticメソッドを呼び出す。
     *
     * @param clazz 呼び出し対象クラス
     * @param methodName 呼び出し対象メソッド名
     * @param args 引数リスト
     * @param argClasses 引数の型リスト
     * @throws SecurityException セキュリティ・マネージャの例外
     * @throws NoSuchMethodException 一致するメソッドが見つからない場合
     * @throws InvocationTargetException 基本となるメソッドが例外をスローする場合
     * @throws IllegalArgumentException メソッド引数異常の場合
     * @throws IllegalAccessException メソッドアクセス異常の場合
     */
    public static void invokePrivateStaticVoidMethod(
            Class<?> clazz,
            String methodName,
            List<Object> args,
            List<Class<?>> argClasses)
            throws NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method method = clazz.getDeclaredMethod(methodName, argClasses.toArray(new Class[0]));
        method.setAccessible(true);
        method.invoke(clazz, args.toArray(new Object[0]));
    }

}
