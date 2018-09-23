package whatsapp_thread;

import eventb_prelude.*;
import Util.Utilities;

public class forward extends Thread{
	/*@ spec_public */ private machine0 machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public forward(machine0 m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_user().has(u) && us.isSubset(machine.get_user()) && (machine.get_muted().image(new BSet<Integer>(u)).intersection(us)).equals(BSet.EMPTY) && (machine.get_muted().image(us).intersection(new BSet<Integer>(u))).equals(BSet.EMPTY) && machine.get_content().has(c) && machine.get_chatcontent().domain().has(u) && us.isSubset(machine.get_chat().image(new BSet<Integer>(u)))); */
	public /*@ pure */ boolean guard_forward( Integer c, Integer u, BSet<Integer> us) {
		return (machine.get_user().has(u) && us.isSubset(machine.get_user()) && (machine.get_muted().image(new BSet<Integer>(u)).intersection(us)).equals(BSet.EMPTY) && (machine.get_muted().image(us).intersection(new BSet<Integer>(u))).equals(BSet.EMPTY) && machine.get_content().has(c) && machine.get_chatcontent().domain().has(u) && us.isSubset(machine.get_chat().image(new BSet<Integer>(u))));
	}

	/*@ public normal_behavior
		requires guard_forward(c,u,us);
		assignable machine.chatcontent, machine.content, machine.chat;
		ensures guard_forward(c,u,us) &&  machine.get_chatcontent().equals(\old((machine.get_chatcontent().override(new BRelation<Integer,BRelation<Integer,BSet<Integer>>>(new Pair<Integer,BRelation<Integer,BSet<Integer>>>(u,(machine.get_chatcontent().apply(u).union(new BRelation<Integer,BSet<Integer>>(new Pair<Integer,BSet<Integer>>(c,us)))))))))) &&  machine.get_content().equals(\old((machine.get_content().union(new BSet<Integer>(c))))) &&  machine.get_chat().equals(\old((machine.get_chat().union(BRelation.cross(us,new BSet<Integer>(u)))))); 
	 also
		requires !guard_forward(c,u,us);
		assignable \nothing;
		ensures true; */
	public void run_forward( Integer c, Integer u, BSet<Integer> us){
		if(guard_forward(c,u,us)) {
			BRelation<Integer,BRelation<Integer,BSet<Integer>>> chatcontent_tmp = machine.get_chatcontent();
			BSet<Integer> content_tmp = machine.get_content();
			BRelation<Integer,Integer> chat_tmp = machine.get_chat();

			machine.set_chatcontent((chatcontent_tmp.override(new BRelation<Integer,BRelation<Integer,BSet<Integer>>>(new Pair<Integer,BRelation<Integer,BSet<Integer>>>(u,(chatcontent_tmp.apply(u).union(new BRelation<Integer,BSet<Integer>>(new Pair<Integer,BSet<Integer>>(c,us)))))))));
			machine.set_content((content_tmp.union(new BSet<Integer>(c))));
			machine.set_chat((chat_tmp.union(BRelation.cross(us,new BSet<Integer>(u)))));

			System.out.println("forward executed c: " + c + " u: " + u + " us: " + us + " ");
		}
	}

	public void run() {
		while(true) {
			Integer c = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Integer u = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			BSet<Integer> us = Utilities.someSet(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			machine.lock.lock(); // start of critical section
			run_forward(c,u,us);
			machine.lock.unlock(); // end of critical section
		}
	}
}
