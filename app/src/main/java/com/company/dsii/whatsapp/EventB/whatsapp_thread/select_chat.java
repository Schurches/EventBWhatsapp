package whatsapp_thread;

import eventb_prelude.*;
import Util.Utilities;

public class select_chat extends Thread{
	/*@ spec_public */ private machine0 machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public select_chat(machine0 m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_user().has(u1) && machine.get_user().has(u2) && machine.get_chat().has(new Pair<Integer,Integer>(u1,u2)) && !machine.get_muted().has(new Pair<Integer,Integer>(u1,u2)) && !machine.get_active().has(new Pair<Integer,Integer>(u1,u2))); */
	public /*@ pure */ boolean guard_select_chat( Integer u1, Integer u2) {
		return (machine.get_user().has(u1) && machine.get_user().has(u2) && machine.get_chat().has(new Pair<Integer,Integer>(u1,u2)) && !machine.get_muted().has(new Pair<Integer,Integer>(u1,u2)) && !machine.get_active().has(new Pair<Integer,Integer>(u1,u2)));
	}

	/*@ public normal_behavior
		requires guard_select_chat(u1,u2);
		assignable machine.active;
		ensures guard_select_chat(u1,u2) &&  machine.get_active().equals(\old((machine.get_active().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(u1,u2)))))); 
	 also
		requires !guard_select_chat(u1,u2);
		assignable \nothing;
		ensures true; */
	public void run_select_chat( Integer u1, Integer u2){
		if(guard_select_chat(u1,u2)) {
			BRelation<Integer,Integer> active_tmp = machine.get_active();

			machine.set_active((active_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(u1,u2)))));

			System.out.println("select_chat executed u1: " + u1 + " u2: " + u2 + " ");
		}
	}

	public void run() {
		while(true) {
			Integer u1 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Integer u2 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			machine.lock.lock(); // start of critical section
			run_select_chat(u1,u2);
			machine.lock.unlock(); // end of critical section
		}
	}
}
