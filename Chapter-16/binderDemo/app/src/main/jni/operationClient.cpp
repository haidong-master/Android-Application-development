#include "IOperation.h"
#include "operationClient.h"


#define PLOG aout  

using namespace android;

BpOperationClient::BpOperationClient(const sp<IBinder>& impl): BpInterface<IOperation>(impl)  {
    ALOGD("BpOperationClient::BpOperationClient()");
}

void BpOperationClient::push(int32_t push_data) {
    Parcel data, reply;
    data.writeInterfaceToken(IOperation::getInterfaceDescriptor());
    data.writeInt32(push_data);

    aout << "BpOperationClient::push parcel to be sent:\n";
    data.print(PLOG);
    endl(PLOG);

    remote()->transact(PUSH, data, &reply);

    aout << "BpOperationClient::push parcel reply:\n";
    reply.print(PLOG);
    endl(PLOG);

    ALOGD("BpOperationClient::push(%i)", push_data);
}

void BpOperationClient::alert() {
    Parcel data, reply;
    data.writeInterfaceToken(IOperation::getInterfaceDescriptor());
    data.writeString16(String16("The alert string"));
    remote()->transact(ALERT, data, &reply, IBinder::FLAG_ONEWAY);    // asynchronous call
    ALOGD("BpOperationClient::alert()");
}

int32_t BpOperationClient::add(int32_t v1, int32_t v2) {
    Parcel data, reply;
    data.writeInterfaceToken(IOperation::getInterfaceDescriptor());
    data.writeInt32(v1);
    data.writeInt32(v2);
    aout << "BpOperationClient::add parcel to be sent:\n";
    data.print(PLOG); endl(PLOG);
    remote()->transact(ADD, data, &reply);
    ALOGD("BpOperationClient::add transact reply");
    reply.print(PLOG); endl(PLOG);

    int32_t res;
    status_t status = reply.readInt32(&res);
    ALOGD("BpOperationClient::add(%i, %i) = %i (status: %i)", v1, v2, res, status);
    return res;
}

//IMPLEMENT_META_INTERFACE(Operation, "OperationService");

android::sp<IOperation> IOperation::asInterface(const android::sp<android::IBinder>& obj) {
     android::sp<IOperation> intr;
     if (obj != NULL) {
            intr = static_cast<IOperation*>(obj->queryLocalInterface(IOperation::descriptor).get());
            if (intr == NULL) {
                intr = new BpOperationClient(obj);
         }
     }
     return intr;
}

