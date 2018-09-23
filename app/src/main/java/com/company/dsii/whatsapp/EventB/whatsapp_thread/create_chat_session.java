package whatsapp_thread; 

import eventb_prelude.*;
import Util.Utilities;

public class create_chat_session extends Thread{
	/*@ spec_public */ private machine0 machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public create_chat_session(machine0 m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_user().has(u1) && machine.get_user().has(u2) && !machine.get_chat().has(new Pair<Integer,Integer>(u1,u2))); */
	public /*@ pure */ boolean guard_create_chat_session( Integer u1, Integer u2) {
		return (machine.get_user().has(u1) && machine.get_user().has(u2) && !machine.get_chat().has(new Pair<Integer,Integer>(u1,u2)));
	}

	/*@ public normal_behavior
		requires guard_create_chat_session(u1,u2);
		assignable machine.chat, machine.active;
		ensures guard_create_chat_session(u1,u2) &&  machine.get_chat().equals(\old((machine.get_chat().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(u1,u2)))))) &&  machine.get_active().equals(\old((machine.get_active().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(u1,u2)))))); 
	 also
		requires !guard_create_chat_session(u1,u2);
		assignable \nothing;
		ensures true; */
	public void run_create_chat_session( Integer u1, Integer u2){
		if(guard_create_chat_session(u1,u2)) {
			BRelation<Integer,Integer> chat_tmp = machine.get_chat();
			BRelation<Integer,Integer> active_tmp = machine.get_active();

			machine.set_chat((chat_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(u1,u2)))));
			machine.set_active((active_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(u1,u2)))));

			System.out.println("create_chat_session executed u1: " + u1 + " u2: " + u2 + " ");
		}
	}

	public void run() {
		while(true) {
			Integer u1 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Integer u2 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			machine.lock.lock(); // start of critical section
			run_create_chat_session(u1,u2);
			machine.lock.unlock(); // end of critical section
		}
	}
}