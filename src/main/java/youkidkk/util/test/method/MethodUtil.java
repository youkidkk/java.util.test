package youkidkk.util.test.method;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
     * @throws Exception 例外時
     */
    @SuppressWarnings("unchecked")
    public static <T> T invokePrivateConstructorWithNoArgs(Class<?> targetClass)
            throws Exception {
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
     * @throws Exception 例外時
     */
    @SuppressWarnings("unchecked")
    public static <T> T invokePrivateConstructorWithArgs(
            Class<?> targetClass,
            List<Object> args,
            List<Class<?>> argClasses)
            throws Exception {
        Constructor<?> constructor = targetClass
                .getDeclaredConstructor(argClasses.toArray(new Class[0]));
        constructor.setAccessible(true);
        return (T) constructor.newInstance(args.toArray(new Object[0]));
    }

    /**
     * privateコンストラクタを呼び出す。
     *
     * @param <T> 戻り値の型
     * @param targetClass 呼び出し対象クラス
     * @param args 引数リスト
     * @return 実行したコンストラクタの戻り値
     * @throws Exception 例外時
     */
    public static <T> T invokePrivateConstructor(
            Class<?> targetClass,
            Object... args)
            throws Exception {
        if (args == null || args.length == 0) {
            return invokePrivateConstructorWithNoArgs(targetClass);
        } else {
            return invokePrivateConstructorWithArgs(targetClass, Arrays.asList(args),
                    mapToPrimitiveClassList(args));
        }
    }

    /**
     * 引数なしのprivateメソッドを呼び出す。
     *
     * @param <T> 戻り値の型
     * @param targetClass 呼び出し対象クラス
     * @param targetObject 呼び出し対象オブジェクト
     * @param targetMethodName 呼び出し対象メソッド名
     * @return 実行したメソッドの戻り値
     * @throws Exception 例外時
     */
    @SuppressWarnings("unchecked")
    public static <T> T invokePrivateMethodWithNoArgs(
            Class<?> targetClass,
            Object targetObject,
            String targetMethodName)
            throws Exception {
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
     * @throws Exception 例外時
     */
    @SuppressWarnings("unchecked")
    public static <T> T invokePrivateMethodWithArgs(
            Class<?> targetClass,
            Object targetObject,
            String targetMethodName,
            List<Object> args,
            List<Class<?>> argClasses)
            throws Exception {
        Method method = targetClass.getDeclaredMethod(
                targetMethodName, argClasses.toArray(new Class[0]));
        method.setAccessible(true);
        return (T) method.invoke(targetObject, args.toArray(new Object[0]));
    }

    /**
     * privateメソッドを呼び出す。
     *
     * @param <T> 戻り値の型
     * @param targetObject 呼び出し対象オブジェクト
     * @param targetMethodName 呼び出し対象メソッド名
     * @param args 引数リスト
     * @return 実行したメソッドの戻り値
     * @throws Exception 例外時
     */
    @SuppressWarnings("unchecked")
    public static <T> T invokePrivateMethod(
            Object targetObject,
            String targetMethodName,
            Object... args)
            throws Exception {
        if (args == null || args.length == 0) {
            return (T) invokePrivateMethodWithNoArgs(targetObject.getClass(), targetObject,
                    targetMethodName);
        } else {
            return (T) invokePrivateMethodWithArgs(targetObject.getClass(), targetObject,
                    targetMethodName, Arrays.asList(args), mapToPrimitiveClassList(args));
        }
    }

    /**
     * 戻り値のない引数なしのprivateメソッドを呼び出す。
     *
     * @param targetClass 呼び出し対象クラス
     * @param targetObject 呼び出し対象オブジェクト
     * @param targetMethodName 呼び出し対象メソッド名
     * @throws Exception 例外時
     */
    public static void invokePrivateVoidMethodWithNoArgs(
            Class<?> targetClass,
            Object targetObject,
            String targetMethodName)
            throws Exception {
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
     * @throws Exception 例外時
     */
    public static void invokePrivateVoidMethodWithArgs(
            Class<?> targetClass,
            Object targetObject,
            String targetMethodName,
            List<Object> args,
            List<Class<?>> argClasses)
            throws Exception {
        Method method = targetClass.getDeclaredMethod(
                targetMethodName, argClasses.toArray(new Class[0]));
        method.setAccessible(true);
        method.invoke(targetObject, args.toArray(new Object[0]));
    }

    /**
     * 戻り値のないprivateメソッドを呼び出す。
     *
     * @param targetObject 呼び出し対象オブジェクト
     * @param targetMethodName 呼び出し対象メソッド名
     * @param args 引数リスト
     * @throws Exception 例外時
     */
    public static void invokePrivateVoidMethod(
            Object targetObject,
            String targetMethodName,
            Object... args)
            throws Exception {
        if (args == null || args.length == 0) {
            invokePrivateVoidMethodWithNoArgs(targetObject.getClass(), targetObject,
                    targetMethodName);
        } else {
            invokePrivateVoidMethodWithArgs(targetObject.getClass(), targetObject,
                    targetMethodName, Arrays.asList(args), mapToPrimitiveClassList(args));
        }
    }

    /**
     * 引数なしのprivate-staticメソッドを呼び出す。
     *
     * @param <T> 戻り値の型
     * @param targetClass 呼び出し対象クラス
     * @param targetMethodName 呼び出し対象メソッド名
     * @return 実行したメソッドの戻り値
     * @throws Exception 例外時
     */
    @SuppressWarnings("unchecked")
    public static <T> T invokePrivateStaticMethodWithNoArgs(
            Class<?> targetClass,
            String targetMethodName)
            throws Exception {
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
     * @throws Exception 例外時
     */
    @SuppressWarnings("unchecked")
    public static <T> T invokePrivateStaticMethodWithArgs(
            Class<?> targetClass,
            String targetMethodName,
            List<Object> args,
            List<Class<?>> argClasses)
            throws Exception {
        Method method = targetClass.getDeclaredMethod(
                targetMethodName, argClasses.toArray(new Class[0]));
        method.setAccessible(true);
        return (T) method.invoke(targetClass, args.toArray(new Object[0]));
    }

    /**
     * private-staticメソッドを呼び出す。
     *
     * @param <T> 戻り値の型
     * @param targetClass 呼び出し対象クラス
     * @param targetMethodName 呼び出し対象メソッド名
     * @param args 引数リスト
     * @return 実行したメソッドの戻り値
     * @throws Exception 例外時
     */
    @SuppressWarnings("unchecked")
    public static <T> T invokePrivateStaticMethod(
            Class<?> targetClass,
            String targetMethodName,
            Object... args)
            throws Exception {
        if (args == null || args.length == 0) {
            return (T) invokePrivateStaticMethodWithNoArgs(targetClass, targetMethodName);
        } else {
            return (T) invokePrivateStaticMethodWithArgs(targetClass, targetMethodName,
                    Arrays.asList(args), mapToPrimitiveClassList(args));
        }
    }

    /**
     * 戻り値のない引数なしのprivate-staticメソッドを呼び出す。
     *
     * @param targetClass 呼び出し対象クラス
     * @param targetMethodName 呼び出し対象メソッド名
     * @throws Exception 例外時
     */
    public static void invokePrivateStaticVoidMethodWithNoArgs(
            Class<?> targetClass,
            String targetMethodName)
            throws Exception {
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
     * @throws Exception 例外時
     */
    public static void invokePrivateStaticVoidMethodWithArgs(
            Class<?> targetClass,
            String targetMethodName,
            List<Object> args,
            List<Class<?>> argClasses)
            throws Exception {
        Method method = targetClass.getDeclaredMethod(
                targetMethodName, argClasses.toArray(new Class[0]));
        method.setAccessible(true);
        method.invoke(targetClass, args.toArray(new Object[0]));
    }

    /**
     * 戻り値のないprivate-staticメソッドを呼び出す。
     *
     * @param targetClass 呼び出し対象クラス
     * @param targetMethodName 呼び出し対象メソッド名
     * @param args 引数リスト
     * @throws Exception 例外時
     */
    public static void invokePrivateStaticVoidMethod(
            Class<?> targetClass,
            String targetMethodName,
            Object... args)
            throws Exception {
        if (args == null || args.length == 0) {
            invokePrivateStaticVoidMethodWithNoArgs(targetClass, targetMethodName);
        } else {
            invokePrivateStaticVoidMethodWithArgs(targetClass, targetMethodName,
                    Arrays.asList(args), mapToPrimitiveClassList(args));
        }
    }

    /**
     * オブジェクト配列にプリミティブ型のラッパークラスが含まれる場合に、プリミティブ型に変換しクラス型のリストとして返却する。
     *
     * @param objects オブジェクト配列
     * @return 変換後のクラス型のリスト
     */
    private static List<Class<?>> mapToPrimitiveClassList(Object[] objects) {
        return Arrays.asList(objects).stream()
                .map(arg -> mapToPrimitiveClass(arg.getClass()))
                .collect(Collectors.toList());
    }

    /**
     * 対象のクラスがプリミティブ型のラッパークラスの場合に、プリミティブ型に変換する。
     *
     * @param target 対象のクラス
     * @return 変換後クラス
     */
    private static Class<?> mapToPrimitiveClass(Class<?> target) {
        Class<?> result;
        if (target.equals(Byte.class)) {
            result = byte.class;
        } else if (target.equals(Short.class)) {
            result = short.class;
        } else if (target.equals(Integer.class)) {
            result = int.class;
        } else if (target.equals(Long.class)) {
            result = long.class;
        } else if (target.equals(Character.class)) {
            result = char.class;
        } else if (target.equals(Float.class)) {
            result = float.class;
        } else if (target.equals(Double.class)) {
            result = double.class;
        } else if (target.equals(Boolean.class)) {
            result = boolean.class;
        } else {
            result = target;
        }
        return result;
    }

}
