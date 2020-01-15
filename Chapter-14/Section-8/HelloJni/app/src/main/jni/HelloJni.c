//
// Created by luhd on 2016/4/24.
//

#include <jni.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

#include <android/log.h>

#define  LOG_TAG    "hello_jni"
#define  LOGI(...)  __android_log_print(ANDROID_LOG_INFO, LOG_TAG,__VA_ARGS__)
#define  LOGE(...)  __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__)


jstring Java_com_demo_hellojni_MainActivity_getStringFromJNI( JNIEnv* env,
                                                  jobject thiz )
{
	LOGI("JNI begin");
    return (*env)->NewStringUTF(env, "Hello JNI !");
}

jint Java_com_demo_hellojni_MainActivity_getIntFromJNI( JNIEnv* env,
                                                  jobject thiz,
                                                  jint a, jint b)
{
    return a + b;
}

void Java_com_demo_hellojni_MainActivity_sendStringToJNI( JNIEnv* env,
                                                  jobject thiz,
                                                  jstring str, jstring filename)
{
	char * m_str = (*env)->GetStringUTFChars(env, str, JNI_FALSE);
	LOGI("char * = %s", m_str);
	if(m_str == NULL)
	{
	//	LOGI("error in get char*");
		return;
	}
	char * c_file = (*env)->GetStringCritical(env, filename, 0);
	if(c_file == NULL)
	{
	//	LOGI("error in get char*");
		return;
	}
	LOGI("char * = %s", c_file);

	//jstring2String(env,filename);
	FILE* file = NULL;
	file = fopen(c_file, "w");
	fprintf(file, " %s", m_str);
	fclose(file);
	return;
}
JNIEXPORT void JNICALL Java_com_demo_hellojni_MainActivity_sendPtrToJNI(JNIEnv* env,
												 jobject thiz,
												 jint w, jint h, jbyteArray data, jintArray num)
{
    char* _data  = (char*)(*env)->GetByteArrayElements(env, data, 0);
    int*  _num = (int*)(*env)->GetIntArrayElements(env, num, 0);

    int i = 0;
    strcpy(_data,"0123456789");
    LOGI("%s", _data);

    for(i = 0; i < 10; i++){
    	_num[i] = 10-i;
    	LOGI("%d", _num[i]);
    }

    (*env)->ReleaseByteArrayElements(env, data, _data, 0);
    (*env)->ReleaseIntArrayElements(env, num, _num, 0);
}
