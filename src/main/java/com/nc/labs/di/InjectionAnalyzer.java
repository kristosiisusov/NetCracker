package com.nc.labs.di;

import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class InjectionAnalyzer {
    public static Object injViaField(String prefixWhere, String prefixFrom) throws BindingException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Reflections findAnnotationRef = new Reflections(prefixWhere, new FieldAnnotationsScanner());
        Reflections reflections;
        Object obj;
        for (Field field : findAnnotationRef.getFieldsAnnotatedWith(Injection.class)) {
            obj = field.getDeclaringClass().getConstructor().newInstance();
            reflections = new Reflections(prefixFrom);
            Set<Class<?>> classes = reflections.getSubTypesOf((Class<Object>) field.getType());
            if (classes.size() > 1) {
                throw new BindingException("Choose needing class to inject:" + classes.toString());
            } else {
                if (classes.stream().findFirst().isPresent()) {
                    field.setAccessible(true);
                    field.set(obj, classes.stream().findFirst().get().getConstructor().newInstance());
                } else {
                    throw new BindingException("No matching elements were found");
                }
            }
            return obj;
        }
        return null;
    }
}
