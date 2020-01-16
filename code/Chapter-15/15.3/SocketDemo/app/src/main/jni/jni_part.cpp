#include <jni.h>
#include "client.h"

Client* mClient;

extern "C" {
JNIEXPORT jint JNICALL Java_com_android_socketdemo_SocketClientJNI_getSocket(JNIEnv* env, jobject thiz)
{
	//struct C*
	mClient = new Client();
	return mClient->getSocket();
}
JNIEXPORT void JNICALL Java_com_android_socketdemo_SocketClientJNI_sendBuf(JNIEnv* env, jobject thiz, jstring str)
{
	//struct C*
	const char * m_str = env->GetStringUTFChars(str, JNI_FALSE);
	LOGI("char * = %s", m_str);
	if(m_str == NULL)
	{
		LOGI("error in get char*");
		return;
	}
	if(mClient->ret != -1){
		LOGI("JNIsendBuf ret != -1");
		mClient->sendBuf(m_str);
	}
}
}
