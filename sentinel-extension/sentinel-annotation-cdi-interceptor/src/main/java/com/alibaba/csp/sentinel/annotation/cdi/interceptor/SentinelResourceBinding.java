/*
 * Copyright 1999-2020 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.csp.sentinel.annotation.cdi.interceptor;

import com.alibaba.csp.sentinel.EntryType;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;

import java.lang.annotation.*;

/**
 * The annotation indicates a definition of Sentinel resource.
 *
 * @author Eric Zhao
 * @author seasidesky
 * @since 1.8.0
 */
@InterceptorBinding
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SentinelResourceBinding {

    /**
     * @return Sentinel资源的名称
     */
    @Nonbinding
    String value() default "";

    /**
     * @return 入口类型(入站或出站)，默认为出站
     */
    @Nonbinding
    EntryType entryType() default EntryType.OUT;

    /**
     * @return资源的分类(类型)
     */
    @Nonbinding
    int resourceType() default 0;

    /**
     * @return 块异常函数的名称，默认为空
     */
    @Nonbinding
    String blockHandler() default "";

    /**
     * 默认情况下，{@code blockHandler}与原始方法位于同一个类中。但是，如果一些方法共享相同的签名并打算设置相同的块处理程序，
     * 那么用户可以在块处理程序存在的地方设置类。注意，块处理程序方法必须是静态的。
     *
     * @return 块处理程序所在的类不应该提供多个类
     */
    @Nonbinding
    Class<?>[] blockHandlerClass() default {};

    /**
     * @return 回退函数的名称，默认为空
     */
    @Nonbinding
    String fallback() default "";

    /**
     * {@code defaultFallback}被用作默认的通用回退方法。
     * 它不应该接受任何参数，并且返回类型应该与原始方法兼容。
     *
     * @return name of the default fallback method, empty by default
     */
    @Nonbinding
    String defaultFallback() default "";

    /**
     * 默认情况下，{@code回退}与原始方法位于同一个类中。但是，
     * 如果一些方法共享相同的签名并打算设置相同的回退，那么用户可以设置存在回退函数的类。注意，共享回退方法必须是静态的。
     *
     * @return the class where the fallback method is located (only single class)
     */
    @Nonbinding
    Class<?>[] fallbackClass() default {};

    /**
     * @return the list of exception classes to trace, {@link Throwable} by default
     */
    @Nonbinding
    Class<? extends Throwable>[] exceptionsToTrace() default {Throwable.class};

    /**
     *表示要忽略的异常。注意{@code exceptionsToTrace}不应该与
     * {@code exceptionsToIgnore}同时出现，否则{@code exceptionsToIgnore}将具有更高的优先级。
     *
     * @return the list of exception classes to ignore, empty by default
     */
    @Nonbinding
    Class<? extends Throwable>[] exceptionsToIgnore() default {};
}
