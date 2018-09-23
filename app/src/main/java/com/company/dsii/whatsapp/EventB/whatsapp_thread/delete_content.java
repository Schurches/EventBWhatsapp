package whatsapp_thread;

import eventb_prelude.*;
import Util.Utilities;

public class delete_content extends Thread{
	/*@ spec_public */ private machine0 machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public delete_content(machine0 m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_user().has(u1) && machine.get_user().has(u2) && machine.get_active().has(new Pair<Integer,Integer>(u1,u2)) && machine.get_chatcontent().domain().has(u1) && machine.get_chatcontent().apply(u1).domain().has(c) && machine.get_chatcontent().apply(u1).apply(c).has(u2)); */
	public /*@ pure */ boolean guard_delete_content( Integer c, Integer u1, Integer u2) {
		return (machine.get_user().has(u1) && machine.get_user().has(u2) && machine.get_active().has(new Pair<Integer,Integer>(u1,u2)) && machine.get_chatcontent().domain().has(u1) && machine.get_chatcontent().apply(u1).domain().has(c) && machine.get_chatcontent().apply(u1).apply(c).has(u2));
	}

	/*@ public normal_behavior
		requires guard_delete_content(c,u1,u2);
		assignable machine.chatcontent;
		ensures guard_delete_content(c,u1,u2) &&  machine.get_chatcontent().equals(\old((machine.get_chatcontent().override(new BRelation<Integer,BRelation<Integer,BSet<Integer>>>(new Pair<Integer,BRelation<Integer,BSet<Integer>>>(u1,(machine.get_chatcontent().apply(u1).override(new BRelation<Integer,BSet<Integer>>(new Pair<Integer,BSet<Integer>>(c,machine.get_chatcontent().apply(u1).apply(c).difference(new BSet<Integer>(u2)))))))))))); 
	 also
		requires !guard_delete_content(c,u1,u2);
		assignable \nothing;
		ensures true; */
	public void run_delete_content( Integer c, Integer u1, Integer u2){
		if(guard_delete_content(c,u1,u2)) {
			BRelation<Integer,BRelation<Integer,BSet<Integer>>> chatcontent_tmp = machine.get_chatcontent();

			machine.set_chatcontent((chatcontent_tmp.override(new BRelation<Integer,BRelation<Integer,BSet<Integer>>>(new Pair<Integer,BRelation<Integer,BSet<Integer>>>(u1,(chatcontent_tmp.apply(u1).override(new BRelation<Integer,BSet<Integer>>(new Pair<Integer,BSet<Integer>>(c,chatcontent_tmp.apply(u1).apply(c).difference(new BSet<Integer>(u2)))))))))));

			System.out.println("delete_content executed c: " + c + " u1: " + u1 + " u2: " + u2 + " ");
		}
	}

	public void run() {
		while(true) {
			Integer c = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Integer u1 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Integer u2 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			machine.lock.lock(); // start of critical section
			run_delete_content(c,u1,u2);
			machine.lock.unlock(); // end of critical section
		}
	}
}
