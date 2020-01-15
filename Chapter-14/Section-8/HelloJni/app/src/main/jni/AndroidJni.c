/*
  * Copyright (C) 2009 The Android Open Source Project
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
  *
  */
 #include <string.h>
 #include <jni.h>
 #include "com_demo_hellojni_AndroidJni.h"
 #include <android/Log.h>

#define  LOG_TAG    "android_jni"
#define  LOGV(...) __android_log_print(ANDROID_LOG_VERBOSE, LOG_TAG, __VA_ARGS__)
#define  LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,   LOG_TAG, __VA_ARGS__)
#define  LOGI(...) __android_log_print(ANDROID_LOG_INFO,    LOG_TAG, __VA_ARGS__)
#define  LOGW(...) __android_log_print(ANDROID_LOG_WARN,    LOG_TAG, __VA_ARGS__)
#define  LOGE(...) __android_log_print(ANDROID_LOG_ERROR,   LOG_TAG, __VA_ARGS__)

#ifndef NELEM
# define NELEM(x) ((int) (sizeof(x) / sizeof((x)[0])))
#endif

#ifdef __cplusplus
extern "C" {
#endif

static const char* className = "com/demo/hellojni/AndroidJni";

int native_max(JNIEnv* env, jobject this, int a, int b)
{
	return  (a > b ? a:b);
}
jstring stringFromJNI(JNIEnv* env, jobject this)
{
	return (*env)->NewStringUTF(env, "Hello form JNI!");
}

static JNINativeMethod gMethods[] = {
   { "stringFromJNI", "()Ljava/lang/String;", (void *)stringFromJNI },
   { "max", "(II)I", (void *)native_max },
 };
 // This function only registers the native methods, and is called from JNI_OnLoad
 int register_location_methods(JNIEnv *env)
 {
     jclass clazz;

     /* look up the class */
     clazz = (*env)->FindClass(env, className );
     //clazz = env->FindClass(env, className);
     if (clazz == NULL) {
         LOGE("Can't find class %s\n", className);
         return -1;
     }

     LOGD("register native methods");

     /* register all the methods */
     if((*env)->RegisterNatives(env, clazz, gMethods, NELEM(gMethods)))
     //if ((*env)->RegisterNatives(env, clazz, gMethods, sizeof(gMethods) / sizeof(gMethods[0])) != JNI_OK)
     {
         LOGE("Failed registering methods for %s\n", className);
         return -1;
     }

     /* fill out the rest of the ID cache */
     return 0;
 }

 jint JNI_OnLoad(JavaVM* vm, void *reserved)
 {
     JNIEnv* env = NULL;
     jint result = -1;

     LOGD("%s: +", __FUNCTION__);

     // for c
     if ((*vm)->GetEnv(vm, (void**) &env, JNI_VERSION_1_6) != JNI_OK) {
     // for c++
     //if( vm->GetEnv((void**) &env, JNI_VERSION_1_4) != JNI_OK) {
         LOGE("ERROR: GetEnv failed.\n");
         return result;
     }

     if( register_location_methods(env) < 0 )
     {
         LOGE("ERROR: register location methods failed.\n");
         return result;
     }

     return JNI_VERSION_1_4;
 }

 void JNI_OnUnload(JavaVM* vm, void *reserved)
 {
     return;
 }

 #ifdef __cplusplus
 }
 #endif



