/*
 * androidScoket.c
 *
 *  Created on: 2013-5-31
 *      Author: lenovo
 */

#include <android/log.h>
#include "client.h"
const char *server= "192.168.1.104";
const int port = 9999;
Client::Client()
{
	ret = -1;
}
Client::~Client()
{
	if(ret != -1)
		close(sock);
}

void Client::sendBuf(const char* buf)
{
	ret = send(sock,buf,strlen(buf),0);
}
char* Client::revBuf(char* buf)
{
	ret = recv(sock,buf,sizeof(buf),0);
}
//#define DEFAULT_PROTO SOCK_STREAM
#define DEFAULT_PROTO SOCK_DGRAM
int Client::getSocket()
{
	struct sockaddr_in peerAddr;
	int socket_type = DEFAULT_PROTO;

	peerAddr.sin_family = AF_INET;
	peerAddr.sin_port = htons(port);
	peerAddr.sin_addr.s_addr = inet_addr(server);

	sock = socket(PF_INET, socket_type, 0);
	if(socket_type == SOCK_STREAM)
	{
		int flag_socket = 1;
		int BufLen = 0; //…Ë÷√Œ™1K
	//	setsockopt( sock,  IPPROTO_TCP, TCP_NODELAY,(char*)flag_socket, sizeof(flag_socket));
		setsockopt( sock, SOL_SOCKET, SO_RCVBUF, ( const char* )&BufLen, sizeof( int ) );
		setsockopt( sock, SOL_SOCKET, SO_SNDBUF, ( const char* )&BufLen, sizeof( int ) );
	}

    ret = connect(sock,(struct sockaddr *)&peerAddr,sizeof(peerAddr));

    if(ret < 0){
        close(sock);
        return -1;
    }
    return 0;
}

