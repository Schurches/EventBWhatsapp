package whatsapp_thread; 

import eventb_prelude.*;
import Util.Utilities;

public class delete_chat_session extends Thread{
	/*@ spec_public */ private machine0 machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public delete_chat_session(machine0 m) {
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

	public void run() {
		while(true) {
			machine.lock.lock(); // start of critical section
			run_delete_chat_session();
			machine.lock.unlock(); // end of critical section
		}
	}
}
