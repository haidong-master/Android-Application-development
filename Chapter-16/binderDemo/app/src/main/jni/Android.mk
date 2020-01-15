
LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE_TAGS := eng

LOCAL_MODULE := binder
LOCAL_MODULE_TAGS := optional
LOCAL_SRC_FILES := binder.cpp
LOCAL_SHARED_LIBRARIES := libutils libcutils libbinder
LOCAL_C_INCLUDES += frameworks/base/include system/core/include \
					frameworks/native/include
LOCAL_LDLIBS += -lbinder -llog -lutils -lui
include $(BUILD_EXECUTABLE)

include $(CLEAR_VARS)
LOCAL_MODULE_TAGS := eng

LOCAL_MODULE := binderService
LOCAL_MODULE_TAGS := optional
LOCAL_SRC_FILES := IOperation.cpp  operationService.cpp service_main.cpp
LOCAL_SHARED_LIBRARIES := libutils libcutils libbinder
LOCAL_C_INCLUDES += frameworks/base/include system/core/include \
                    frameworks/native/include
LOCAL_LDLIBS += -lbinder -llog -lutils -lui

include $(BUILD_EXECUTABLE)

include $(CLEAR_VARS)
LOCAL_MODULE_TAGS := eng

LOCAL_MODULE := binderClient
LOCAL_MODULE_TAGS := optional
LOCAL_SRC_FILES := IOperation.cpp operationClient.cpp client_main.cpp
LOCAL_SHARED_LIBRARIES := libutils libcutils libbinder
LOCAL_C_INCLUDES += frameworks/base/include system/core/include \
                    frameworks/native/include
LOCAL_LDLIBS += -lbinder -llog -lutils -lui

include $(BUILD_EXECUTABLE)
 
include $(CLEAR_VARS)
LOCAL_MODULE_TAGS := eng

LOCAL_MODULE := binderSer
LOCAL_MODULE_TAGS := optional
LOCAL_SRC_FILES := IOperation.cpp  operationService.cpp service_main.cpp
LOCAL_SHARED_LIBRARIES := libutils libcutils libbinder
LOCAL_C_INCLUDES += frameworks/base/include system/core/include \
                    frameworks/native/include
LOCAL_LDLIBS += -lbinder -llog -lutils -lui

include $(BUILD_SHARED_LIBRARY)
