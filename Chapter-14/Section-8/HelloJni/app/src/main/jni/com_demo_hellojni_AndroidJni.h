/*
 * com_demo_hellojni_AndroidJni.h
 *
 *  Created on: 2016-4-25
 *      Author: luhd
 */

#ifndef COM_EXAMPLE_HELLOJNI_ANDROIDJNI_H_
#define COM_EXAMPLE_HELLOJNI_ANDROIDJNI_H_


jstring stringFromJNI(JNIEnv* env, jobject this);
int native_max(JNIEnv* env, jobject this, int a, int b);

#endif /* COM_EXAMPLE_HELLOJNI_ANDROIDJNI_H_ */
