//package cn.samir.online.util;
//
//import android.util.Log;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by xiaw on 2017/5/2 0002.
// */
//
//public class JavaCalls {
//
//    private static final String LOG_TAG = "JavaCalls";
//    private static final Map<Class<?>, Class<?>> PRIMITIVE_MAP;
//
//    static {
//        HashMap<Class<?>, Class<?>> localHashMap = new HashMap<Class<?>, Class<?>>();
//        PRIMITIVE_MAP = localHashMap;
//        localHashMap.put(Boolean.class, Boolean.TYPE);
//        PRIMITIVE_MAP.put(Byte.class, Byte.TYPE);
//        PRIMITIVE_MAP.put(Character.class, Character.TYPE);
//        PRIMITIVE_MAP.put(Short.class, Short.TYPE);
//        PRIMITIVE_MAP.put(Integer.class, Integer.TYPE);
//        PRIMITIVE_MAP.put(Float.class, Float.TYPE);
//        PRIMITIVE_MAP.put(Long.class, Long.TYPE);
//        PRIMITIVE_MAP.put(Double.class, Double.TYPE);
//        PRIMITIVE_MAP.put(Boolean.TYPE, Boolean.TYPE);
//        PRIMITIVE_MAP.put(Byte.TYPE, Byte.TYPE);
//        PRIMITIVE_MAP.put(Character.TYPE, Character.TYPE);
//        PRIMITIVE_MAP.put(Short.TYPE, Short.TYPE);
//        PRIMITIVE_MAP.put(Integer.TYPE, Integer.TYPE);
//        PRIMITIVE_MAP.put(Float.TYPE, Float.TYPE);
//        PRIMITIVE_MAP.put(Long.TYPE, Long.TYPE);
//        PRIMITIVE_MAP.put(Double.TYPE, Double.TYPE);
//    }
//
//
//    public static <T> T callMethod(Object paramObject, String paramString, Object... paramVarArgs)
//    {
//        try
//        {
//            paramVarArgs = callMethodOrThrow(paramObject, paramString, paramVarArgs);
//            return paramVarArgs;
//        }
//        catch (Exception e)
//        {
//            Log.w("JavaCalls", "Meet exception when call Method '" + paramString + "' in " + paramObject, paramVarArgs);
//        }
//        return null;
//    }
//
//
//    public static <T> T callMethodOrThrow(Object paramObject, String paramString, Object... paramVarArgs)
//            throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException
//    {
//        return (T)getDeclaredMethod(paramObject.getClass(), paramString, getParameterTypes(paramVarArgs)).invoke(paramObject, getParameters(paramVarArgs));
//    }
//
//
//    private static Method getDeclaredMethod(Class<?> paramClass, String paramString, Class<?>... paramVarArgs)
//            throws NoSuchMethodException, SecurityException
//    {
//        Method localMethod;
//        for (;;)
//        {
//            localMethod = findMethodByName(paramClass.getDeclaredMethods(), paramString, paramVarArgs);
//            if (localMethod != null) {
//                break label37;
//            }
//            if (paramClass.getSuperclass() == null) {
//                break;
//            }
//            paramClass = paramClass.getSuperclass();
//        }
//        throw new NoSuchMethodException();
//        label37:
//        localMethod.setAccessible(true);
//        return localMethod;
//    }
//
//    private static Class<?>[] getParameterTypes(Object... paramVarArgs)
//    {
//        Class[] arrayOfClass = null;
//        Object localObject = arrayOfClass;
//        if (paramVarArgs != null)
//        {
//            localObject = arrayOfClass;
//            if (paramVarArgs.length > 0)
//            {
//                arrayOfClass = new Class[paramVarArgs.length];
//                int i = 0;
//                if (i < paramVarArgs.length)
//                {
//                    localObject = paramVarArgs[i];
//                    if ((localObject != null) && ((localObject instanceof JavaParam))) {
//                        localObject = ((JavaParam)localObject).clazz;
//                    }
//                    for (;;)
//                    {
//                        arrayOfClass[i] = localObject;
//                        i += 1;
//                        break;
//                        if (localObject == null) {
//                            localObject = null;
//                        } else {
//                            localObject = localObject.getClass();
//                        }
//                    }
//                }
//                localObject = arrayOfClass;
//            }
//        }
//        return (Class<?>[])localObject;
//    }
//
//}
