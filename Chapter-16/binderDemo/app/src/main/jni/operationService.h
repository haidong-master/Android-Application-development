#ifndef _OPERATIONSERVICE_H_
#define _OPERATIONSERVICE_H_

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

class BnOperationService : public BnInterface<IOperation> {
    virtual status_t onTransact(uint32_t code, const Parcel& data, Parcel* reply, uint32_t flags = 0);
};

class OperationService : public BnOperationService {
    virtual void push(int32_t data);
    virtual void alert(); 
    virtual int32_t add(int32_t v1, int32_t v2);
};

}
#endif
