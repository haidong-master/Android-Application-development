#include  "IOperation.h"
using namespace android;
const String16 IOperation::descriptor("OperationService");//DESCRIPTOR("OperationService");
const String16& IOperation::getInterfaceDescriptor() const {
       return descriptor;
}
IOperation::IOperation() { ALOGD("IOperation::IOperation()"); }
IOperation::~IOperation() { ALOGD("IOperation::~IOperation()"); }
