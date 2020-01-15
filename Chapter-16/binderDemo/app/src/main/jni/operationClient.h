#ifndef _OPERATIONCLIENT_H_
#define _OPERATIONCLIENT_H_

#include <stdlib.h>

#include "utils/RefBase.h"
#include "utils/Log.h"
#include "utils/TextOutput.h"

#include <binder/IInterface.h>
#include <binder/IBinder.h>
#include <binder/ProcessState.h>
#include <binder/IServiceManager.h>
#include <binder/IPCThreadState.h>

#include "IOperation.h"

namespace android {

class BpOperationClient : public BpInterface<IOperation> 
{
    public:
        BpOperationClient(const sp<IBinder>& impl) ;
        virtual void push(int32_t push_data) ;
        virtual void alert() ;
        virtual int32_t add(int32_t v1, int32_t v2) ;

};
}
#endif
