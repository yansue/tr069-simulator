package com.paraam.cpeagent.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import org.dslforum.cwmp_1_0.Envelope;
import org.dslforum.cwmp_1_0.EventStruct;

public class CPEHttpServer implements Runnable {

	CpeDBReader confdb;	
	String 	username	= null;
	String 	passwd		= null;
	String 	authtype	= null;
	int 	port;

	public CPEHttpServer (CpeDBReader confdb, String username, String passwd, String authtype) {
		this.confdb = confdb;
		this.username 	= username;
		this.passwd 	= passwd;
		this.authtype 	= authtype;
	}

		String reqURL = ((ConfParameter)confdb.confs.get(confdb.props.getProperty("ConnectionRequestURL"))).value;
		//String reqURL = ((ConfParameter)confdb.confs.get("InternetGatewayDevice.ManagementServer.ConnectionRequestURL")).value;
			this.port 			= new URL(reqURL).getPort();
				if (serverSocket == null) {
					break;
				}

				ArrayList<EventStruct> eventKeyList = new ArrayList<EventStruct>();
				EventStruct eventStruct = new EventStruct();
				eventStruct.setEventCode("6 REQUEST");
				eventKeyList.add(eventStruct);
				CpeActions cpeactions = new CpeActions(confdb);
				Envelope informMessage = cpeactions.doInform(eventKeyList);
				
				System.out.println("Sending Connection Request Inform Message at " + (new Date()));				


				CPEClientSession session = new CPEClientSession(cpeactions, username, passwd, authtype);
				session.sendInform(informMessage);

			try {
				serverSocket.close();
			} catch (IOException e) { 	}	
		}
}