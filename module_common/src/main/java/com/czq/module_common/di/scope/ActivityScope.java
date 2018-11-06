/*
 * Copyright 2017 JessYan
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
package com.czq.module_common.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

//@Singleton就是一种Scope注解，也是Dagger2唯一自带的Scope注解,下面是@Singleton的源码
/*
@Scope
@Documented
@Retention(RUNTIME)
public @interface Singleton{}

@Scope ：注明是Scope
@Documented ：标记文档提示
@Retention(RUNTIME) ：运行时级别
*/

@Scope
@Documented
@Retention(RUNTIME)
public @interface ActivityScope {}
