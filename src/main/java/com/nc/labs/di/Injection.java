package com.nc.labs.di;


import java.lang.annotation.*;

/**
 * own annotation to inject dependency
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Injection {

}

