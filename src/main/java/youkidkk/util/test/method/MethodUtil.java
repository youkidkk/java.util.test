package youkidkk.util.test.method;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * メソッド呼び出しユーティリティークラス
 */
public class MethodUtil {

    /**
     * コンストラクタ（呼び出し不可）。
     */
    private MethodUtil() {
    }

    /**
     * 引数なしのprivateコンストラクタを呼び出す。
     *
     * @param <T> 戻り値の型
     * @param targetClass 呼び出し対象クラス
     * @return 実行したコンストラクタの結果
     * @throws NoSuchMethodException 対象のメソッドが見つからない場合
     * @throws SecurityException セキュリティ・マネージャの例外
     * @throws InstantiationException インスタンス化以上の場合
     * @throws IllegalAccessException メソッドアクセス異常の場合
     * @throws IllegalArgumentException メソッド引数異常の場合
     * @throws InvocationTargetException 基本となるメソッドが例外をスローする場合
     */
    @SuppressWarnings("unchecked")
    public static <T> T invokePrivateConstructor(Class<?> targetClass)
            throws NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Constructor<?> constructor = targetClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        return (T) constructor.newInstance();
    }

    /**
     * privateコンストラクタを呼び出す。
     *
     * @param <T> 戻り値の型
     * @param targetClass 呼び出し対象クラス
     * @param args 引数リスト
     * @param argClasses 引数の型リスト
     * @return 実行したコンストラクタの戻り値
     * @throws NoSuchMethodException 対象のメソッドが見つからない場合
     * @throws SecurityException セキュリティ・マネージャの例外
     * @throws InstantiationException インスタンス化以上の場合
     * @throws IllegalAccessException メソッドアクセス異常の場合
     * @throws IllegalArgumentException メソッド引数異常の場合
     * @throws InvocationTargetException 基本となるメソッドが例外をスローする場合
     */
    @SuppressWarnings("unchecked")
    public static <T> T invokePrivateConstructor(
            Class<?> targetClass,
            List<Object> args,
            List<Class<?>> argClasses)
            throws NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Constructor<?> constructor = targetClass
                .getDeclaredConstructor(argClasses.toArray(new Class[0]));
        constructor.setAccessible(true);
        return (T) constructor.newInstance(args.toArray(new Object[0]));
    }

    /**
     * 引数なしのprivateメソッドを呼び出す。
     *
     * @param <T> 戻り値の型
     * @param targetClass 呼び出し対象クラス
     * @param targetObject 呼び出し対象オブジェクト
     * @param targetMethodName 呼び出し対象メソッド名
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
            String targetMethodName)
            throws NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method method = targetClass.getDeclaredMethod(targetMethodName);
        method.setAccessible(true);
        return (T) method.invoke(targetObject);
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
     * 戻り値のない引数なしのprivateメソッドを呼び出す。
     *
     * @param targetClass 呼び出し対象クラス
     * @param targetObject 呼び出し対象オブジェクト
     * @param targetMethodName 呼び出し対象メソッド名
     * @throws SecurityException セキュリティ・マネージャの例外
     * @throws NoSuchMethodException 対象のメソッドが見つからない場合
     * @throws InvocationTargetException 基本となるメソッドが例外をスローする場合
     * @throws IllegalArgumentException メソッド引数異常の場合
     * @throws IllegalAccessException メソッドアクセス異常の場合
     */
    public static void invokePrivateVoidMethod(
            Class<?> targetClass,
            Object targetObject,
            String targetMethodName)
            throws NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method method = targetClass.getDeclaredMethod(targetMethodName);
        method.setAccessible(true);
        method.invoke(targetObject);
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
     * 引数なしのprivate-staticメソッドを呼び出す。
     *
     * @param <T> 戻り値の型
     * @param targetClass 呼び出し対象クラス
     * @param targetMethodName 呼び出し対象メソッド名
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
            String targetMethodName)
            throws NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method method = targetClass.getDeclaredMethod(
                targetMethodName);
        method.setAccessible(true);
        return (T) method.invoke(targetClass);
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
     * 戻り値のない引数なしのprivate-staticメソッドを呼び出す。
     *
     * @param targetClass 呼び出し対象クラス
     * @param targetMethodName 呼び出し対象メソッド名
     * @throws SecurityException セキュリティ・マネージャの例外
     * @throws NoSuchMethodException 対象のメソッドが見つからない場合
     * @throws InvocationTargetException 基本となるメソッドが例外をスローする場合
     * @throws IllegalArgumentException メソッド引数異常の場合
     * @throws IllegalAccessException メソッドアクセス異常の場合
     */
    public static void invokePrivateStaticVoidMethod(
            Class<?> targetClass,
            String targetMethodName)
            throws NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method method = targetClass.getDeclaredMethod(targetMethodName);
        method.setAccessible(true);
        method.invoke(targetClass);
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

}
