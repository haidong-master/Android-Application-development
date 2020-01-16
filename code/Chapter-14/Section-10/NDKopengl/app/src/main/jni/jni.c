#include "game.h"
#include <jni.h>
#if 0
JNIEXPORT void JNICALL Java_com_demo_ndkopengl_JniRenderer_on_1surface_1created(JNIEnv * env, jclass cls) {
	on_surface_created();
}

JNIEXPORT void JNICALL Java_com_demo_ndkopengl_JniRenderer_on_1surface_1changed(JNIEnv * env, jclass cls, jint width, jint height) {
	on_surface_changed();
}

JNIEXPORT void JNICALL Java_com_demo_ndkopengl_JniRenderer_on_1draw_1frame(JNIEnv * env, jclass cls) {
	on_draw_frame();
}

/* Alternative method: use normal method names and register them in a JNI_OnLoad function:
 */
#else

void jni_on_surface_created(JNIEnv* env, jobject this)
{
	on_surface_created();
}
void jni_on_surface_changed(JNIEnv* env, jobject this, jint width, jint height)
{
	on_surface_changed();
}
void jni_on_draw_frame(JNIEnv* env, jobject this)
{
	on_draw_frame();
}


static JNINativeMethod methodTable[] = {
  {"on_surface_created", "()V", (void *) jni_on_surface_created},
  {"on_surface_changed", "(II)V", (void *) jni_on_surface_changed},
  {"on_draw_frame", "()V", (void *) jni_on_draw_frame},
};

jint JNI_OnLoad(JavaVM* vm, void* reserved)
{
    JNIEnv* env;
    if ((*vm)->GetEnv(vm, (void**)&env, JNI_VERSION_1_4) != JNI_OK) {
		return -1;
	}

    (*env)->RegisterNatives(env,
    						(*env)->FindClass(env, "com/demo/ndkopengl/JniRenderer"),
    						methodTable,
    						sizeof(methodTable) / sizeof(methodTable[0]));

    return JNI_VERSION_1_4;
}
void JNI_OnUnload(JavaVM* vm, void *reserved)
{
    return;
}
#endif
