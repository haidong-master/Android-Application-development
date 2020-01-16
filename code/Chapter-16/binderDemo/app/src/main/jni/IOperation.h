#ifndef _IOPERATION_H_
#define _IOPERATION_H_

#include "utils/RefBase.h"
#include "utils/Log.h"
#include "utils/TextOutput.h"

#include <binder/IInterface.h>
#include <binder/IBinder.h>
#include <binder/ProcessState.h>
#include <binder/IServiceManager.h>
#include <binder/IPCThreadState.h>
using namespace android;
namespace android {

class IOperation : public IInterface {
    public:
        enum {
            ALERT = IBinder::FIRST_CALL_TRANSACTION,
            PUSH,
            ADD
        };
        // Sends a user-provided value to the service
        virtual void        push(int32_t data)          = 0;
        // Sends a fixed alert string to the service
        virtual void        alert()                     = 0;
        // Requests the service to perform an addition and return the result
        virtual int32_t     add(int32_t v1, int32_t v2) = 0;

        /**
         * DECLARE_META_INTERFACE is a macro Defined in binder/IInterface.h that
         * implements the BnInterface (native interface)
         *
         * It defines two methods
         * getInterfaceDescriptor (this is meta data)
         * and
         * asInterface(IBinder) to convert a binder object back to the interface.
         *
         * This last method can either return a local(native) or remote(p) implementatation
         * of the interface. It does this by calling the queryLocalInterface on the Binder
         * object and if this object does not exist create a new instance of a class called
         * Bp"interfacename" so in our case BpHelloWorldInterface. Note that the macro takes
         * the interface name without the starting I
         *
         * If you are only implementing the native side of things this macro might be overhead because
         * it requires you to have a Bp (remote) implementation
         **/
        DECLARE_META_INTERFACE(Operation);  

        /*
        static const android::String16 descriptor;
        static android::sp<IOperation> asInterface(const android::sp<android::IBinder>& obj);
        virtual const android::String16& getInterfaceDescriptor() const;
        IOperation(); // { ALOGD("IOperation::IOperation()"); }
        virtual ~IOperation();// { ALOGD("IOperation::~IOperation()"); }
        */
};


}
#endif
