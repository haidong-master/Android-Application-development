/*
 * androidScoket.h
 *
 *  Created on: 2013-5-31
 *      Author: lenovo
 */

#ifndef ANDROIDSCOKET_H_
#define ANDROIDSCOKET_H_

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <fcntl.h>
#include <ctype.h>
#include <unistd.h>
#include <netdb.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>

#include <android/log.h>
#define  LOG_TAG    "CSDemo"
#define  LOGI(...)  __android_log_print(ANDROID_LOG_INFO, LOG_TAG,__VA_ARGS__)
#define  LOGE(...)  __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__)

class Client{
public:
	Client();
	~Client();
	void sendBuf(const char* buf);
	char* revBuf(char* buf);
	int getSocket();
private:
	int sock;
	char sendbuf[1024];
	char revbuf[1024];
public:
	int ret;
};
/*
extern "C" int call_Client_getSocket(Client* p) // wrapper function
{
       return p->getSocket();
}
extern "C" void call_Client_sendBuf(Client* p, char* buf) // wrapper function
{
       return p->sendBuf(buf);
}
extern "C" char* call_Client_revBuf(Client* p, char* buf) // wrapper function
{
       return p->revBuf(buf);
}*/
#endif /* ANDROIDSCOKET_H_ */
