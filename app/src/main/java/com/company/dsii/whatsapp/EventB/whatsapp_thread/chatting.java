package whatsapp_thread;

import eventb_prelude.*;
import Util.Utilities;

public class chatting extends Thread{
	/*@ spec_public */ private machine0 machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public chatting(machine0 m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_user().has(u1) && machine.get_user().has(u2) && machine.get_active().has(new Pair<Integer,Integer>(u1,u2)) && !machine.get_muted().has(new Pair<Integer,Integer>(u1,u2)) && !machine.get_muted().has(new Pair<Integer,Integer>(u2,u1)) && machine.CONTENT.has(c) && !machine.get_content().has(c) && machine.get_chatcontent().domain().has(u1)); */
	public /*@ pure */ boolean guard_chatting( Integer c, Integer u1, Integer u2) {
		return (machine.get_user().has(u1) && machine.get_user().has(u2) && machine.get_active().has(new Pair<Integer,Integer>(u1,u2)) && !machine.get_muted().has(new Pair<Integer,Integer>(u1,u2)) && !machine.get_muted().has(new Pair<Integer,Integer>(u2,u1)) && machine.CONTENT.has(c) && !machine.get_content().has(c) && machine.get_chatcontent().domain().has(u1));
	}

	/*@ public normal_behavior
		requires guard_chatting(c,u1,u2);
		assignable machine.content, machine.chat, machine.chatcontent;
		ensures guard_chatting(c,u1,u2) &&  machine.get_content().equals(\old((machine.get_content().union(new BSet<Integer>(c))))) &&  machine.get_chat().equals(\old((machine.get_chat().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(u2,u1)))))) &&  machine.get_chatcontent().equals(\old((machine.get_chatcontent().override(new BRelation<Integer,BRelation<Integer,BSet<Integer>>>(new Pair<Integer,BRelation<Integer,BSet<Integer>>>(u1,(machine.get_chatcontent().apply(u1).union(new BRelation<Integer,BSet<Integer>>(new Pair<Integer,BSet<Integer>>(c,new BSet<Integer>(u2))))))))))); 
	 also
		requires !guard_chatting(c,u1,u2);
		assignable \nothing;
		ensures true; */
	public void run_chatting( Integer c, Integer u1, Integer u2){
		if(guard_chatting(c,u1,u2)) {
			BSet<Integer> content_tmp = machine.get_content();
			BRelation<Integer,Integer> chat_tmp = machine.get_chat();
			BRelation<Integer,BRelation<Integer,BSet<Integer>>> chatcontent_tmp = machine.get_chatcontent();

			machine.set_content((content_tmp.union(new BSet<Integer>(c))));
			machine.set_chat((chat_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(u2,u1)))));
			machine.set_chatcontent((chatcontent_tmp.override(new BRelation<Integer,BRelation<Integer,BSet<Integer>>>(new Pair<Integer,BRelation<Integer,BSet<Integer>>>(u1,(chatcontent_tmp.apply(u1).union(new BRelation<Integer,BSet<Integer>>(new Pair<Integer,BSet<Integer>>(c,new BSet<Integer>(u2))))))))));

			System.out.println("chatting executed c: " + c + " u1: " + u1 + " u2: " + u2 + " ");
		}
	}

	public void run() {
		while(true) {
			Integer c = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Integer u1 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Integer u2 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			machine.lock.lock(); // start of critical section
			run_chatting(c,u1,u2);
			machine.lock.unlock(); // end of critical section
		}
	}
}
