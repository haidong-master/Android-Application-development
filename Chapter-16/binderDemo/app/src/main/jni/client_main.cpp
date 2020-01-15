#include "operationClient.h"

using namespace android;

int main(int argc, char **argv) {

    ALOGD("We're the client");
    int v = 234;
    sp<IServiceManager> sm = defaultServiceManager();
    sp<IBinder> binder = sm->getService(String16("OperationService"));
    // TODO: If the "Demo" service is not running, getService times out and binder == 0.
    sp<IOperation> demo = interface_cast<IOperation>(binder);
    demo->alert();
    demo->push(v);
    const int32_t adder = 5;
    int32_t sum = demo->add(v, adder);
    ALOGD("Addition result: %i + %i = %i", v, adder, sum);

    return 0;
}
