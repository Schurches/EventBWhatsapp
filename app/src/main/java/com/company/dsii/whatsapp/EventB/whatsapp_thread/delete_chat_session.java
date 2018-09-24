package com.company.dsii.whatsapp.EventB.whatsapp_thread;

import com.company.dsii.whatsapp.EventB.eventb_prelude.*;
import com.company.dsii.whatsapp.EventB.Util.Utilities;

public class delete_chat_session{
	/*@ spec_public */ private machine2 machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public delete_chat_session(machine2 m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> true; */
	public /*@ pure */ boolean guard_delete_chat_session() {
		return true;
	}

	/*@ public normal_behavior
		requires guard_delete_chat_session();
		assignable \nothing;
		ensures guard_delete_chat_session() && true; 
	 also
		requires !guard_delete_chat_session();
		assignable \nothing;
		ensures true; */
	public void run_delete_chat_session(){
		if(guard_delete_chat_session()) {


			System.out.println("delete_chat_session executed ");
		}
	}

}
