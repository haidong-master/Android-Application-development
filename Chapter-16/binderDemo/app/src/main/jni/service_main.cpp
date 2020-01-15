#include <sys/types.h>
#include <sys/stat.h>
#include <dirent.h>
#include <unistd.h>
#include <jni.h>
#include "operationService.h"

using namespace android;

int main(int argc, char **argv) {

    ALOGD("We're the service");

    defaultServiceManager()->addService(String16("OperationService"), new OperationService());
    android::ProcessState::self()->startThreadPool();
    ALOGD("Demo service is now ready");
    IPCThreadState::self()->joinThreadPool();
    ALOGD("Demo service thread joined");

    return 0;
}


jobject nativeInit(JNIEnv *, jobject);


/**
 * Holder for the WM we hope to run in;
 */
static JavaVM *javaVM;

jint JNI_OnLoad(JavaVM* vm, void* reserved){
	JNIEnv *env = NULL;

	jclass clazz;

	/* Keep a reference to the vm. the vm is valid during the full scope
	 * of the library
	 */
	javaVM = vm;
	ALOGI("%s",__FUNCTION__);


	if (vm->GetEnv((void **) &env, JNI_VERSION_1_4) != JNI_OK) {
		return JNI_ERR;
	}

	clazz = env->FindClass("com/example/binderdemo/binderService");
	if (!clazz){
		ALOGI("Failed to load the HelloWorldService class");
	}

	JNINativeMethod sMethods[] = {
    /*
     * funcPtr generated using javah on the service class
     * Class:     org_credil_helloworldservice_HelloWorldService
     * Method:    nativeInit
     * Signature: ()Landroid/os/Binder;
     */
		{"nativeInit","()Landroid/os/Binder;", (void*)nativeInit},
	};


	if (env->RegisterNatives(clazz,sMethods,sizeof(sMethods)/ sizeof(JNINativeMethod)) != JNI_OK){
		ALOGI("%s Failed to register native method",__FUNCTION__);
	}

	return JNI_VERSION_1_4;
}

jobject nativeInit(JNIEnv * env , jobject object){
    //mostly copyed from
    //extern jobject javaObjectForIBinder(JNIEnv* env, const sp<IBinder>& val);
    //The header for this is currently in core/jni/android_util_Binder.h.

	ALOGI("%s What do do with this binder",__FUNCTION__);
	jclass cls = env->FindClass("android/os/Binder");
	if(!cls) {
		ALOGE("%s: failed to get Binder class reference", __FUNCTION__);
		return NULL;
	}

	jmethodID constr = env->GetMethodID(cls, "<init>", "()V");
	if(!constr) {
		ALOGE("%s: failed to get Binder constructor", __FUNCTION__);
		return NULL;
	}

	jobject obj = env->NewObject(cls, constr);
	if(!obj) {
		ALOGE("%s: failed to create a Binder object", __FUNCTION__);
		return NULL;
	}
	jfieldID field  = env->GetFieldID(cls,"mObject","I");
	if(!field){
		ALOGE("%s: failed to get native binder holder");
		return NULL;
	}

	//trying to replace the binder field of the method but this currently crashed the system
	android::IBinder * ref = new android::OperationService();
	env->SetIntField(obj,field,(int)ref);
    //TODO to we need to start/join the threadpool or does this only work if we first fork
	android::ProcessState::self()->startThreadPool();
	return obj;
}

