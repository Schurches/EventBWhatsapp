package whatsapp_thread;

import eventb_prelude.*;
import Util.Utilities;

public class mute_chat extends Thread{
	/*@ spec_public */ private machine0 machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public mute_chat(machine0 m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_user().has(u1) && machine.get_user().has(u2) && machine.get_chat().has(new Pair<Integer,Integer>(u1,u2)) && !machine.get_muted().has(new Pair<Integer,Integer>(u1,u2))); */
	public /*@ pure */ boolean guard_mute_chat( Integer u1, Integer u2) {
		return (machine.get_user().has(u1) && machine.get_user().has(u2) && machine.get_chat().has(new Pair<Integer,Integer>(u1,u2)) && !machine.get_muted().has(new Pair<Integer,Integer>(u1,u2)));
	}

	/*@ public normal_behavior
		requires guard_mute_chat(u1,u2);
		assignable machine.muted, machine.active;
		ensures guard_mute_chat(u1,u2) &&  machine.get_muted().equals(\old((machine.get_muted().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(u1,u2)))))) &&  machine.get_active().equals(\old(machine.get_active().difference(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(u1,u2))))); 
	 also
		requires !guard_mute_chat(u1,u2);
		assignable \nothing;
		ensures true; */
	public void run_mute_chat( Integer u1, Integer u2){
		if(guard_mute_chat(u1,u2)) {
			BRelation<Integer,Integer> muted_tmp = machine.get_muted();
			BRelation<Integer,Integer> active_tmp = machine.get_active();

			machine.set_muted((muted_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(u1,u2)))));
			machine.set_active(active_tmp.difference(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(u1,u2))));

			System.out.println("mute_chat executed u1: " + u1 + " u2: " + u2 + " ");
		}
	}

	public void run() {
		while(true) {
			Integer u1 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Integer u2 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			machine.lock.lock(); // start of critical section
			run_mute_chat(u1,u2);
			machine.lock.unlock(); // end of critical section
		}
	}
}
