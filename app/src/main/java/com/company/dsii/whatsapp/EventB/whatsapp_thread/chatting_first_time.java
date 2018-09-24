package com.company.dsii.whatsapp.EventB.whatsapp_thread;

import com.company.dsii.whatsapp.EventB.eventb_prelude.*;
import com.company.dsii.whatsapp.EventB.Util.Utilities;

public class chatting_first_time{
	/*@ spec_public */ private machine2 machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public chatting_first_time(machine2 m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_user().has(u1) && machine.get_user().has(u2) && machine.get_active().has(new Pair<Integer,Integer>(u1,u2)) && !machine.get_muted().has(new Pair<Integer,Integer>(u1,u2)) && !machine.get_muted().has(new Pair<Integer,Integer>(u2,u1)) && machine.CONTENT.has(c) && !machine.get_content().has(c) && machine.get_chatcontent().domain().has(u1) && machine.get_screen().domain().has(u1) && !machine.get_screen().apply(u1).domain().has(u2) && INT.instance.has(k1) && machine.get_screen().domain().has(u2) && !machine.get_screen().apply(u2).domain().has(u1) && INT.instance.has(k2)); */
	public /*@ pure */ boolean guard_chatting_first_time( Integer c, Integer u1, Integer u2, Integer k1, Integer k2) {
		return (machine.get_user().has(u1) && machine.get_user().has(u2) && machine.get_active().has(new Pair<Integer,Integer>(u1,u2)) && !machine.get_muted().has(new Pair<Integer,Integer>(u1,u2)) && !machine.get_muted().has(new Pair<Integer,Integer>(u2,u1)) && machine.CONTENT.has(c) && !machine.get_content().has(c) && machine.get_chatcontent().domain().has(u1) && machine.get_screen().domain().has(u1) && !machine.get_screen().apply(u1).domain().has(u2) && INT.instance.has(k1) && machine.get_screen().domain().has(u2) && !machine.get_screen().apply(u2).domain().has(u1) && INT.instance.has(k2));
	}

	/*@ public normal_behavior
		requires guard_chatting_first_time(c,u1,u2,k1,k2);
		assignable machine.content, machine.chat, machine.chatcontent, machine.toread, machine.inactive, machine.screen, machine.csize, machine.contents;
		ensures guard_chatting_first_time(c,u1,u2,k1,k2) &&  machine.get_content().equals(\old((machine.get_content().union(new BSet<Integer>(c))))) &&  machine.get_chat().equals(\old((machine.get_chat().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(u2,u1)))))) &&  machine.get_chatcontent().equals(\old((machine.get_chatcontent().override(new BRelation<Integer,BRelation<Integer,BSet<Integer>>>(new Pair<Integer,BRelation<Integer,BSet<Integer>>>(u1,(machine.get_chatcontent().apply(u1).union(new BRelation<Integer,BSet<Integer>>(new Pair<Integer,BSet<Integer>>(c,new BSet<Integer>(u2))))))))))) &&  machine.get_toread().equals(\old((machine.get_toread().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(u2,u1)).difference(machine.get_active()).union((new BRelation<Integer,Integer>(new Pair<Integer,Integer>(u2,u1)).intersection(machine.get_inactive()))))))) &&  machine.get_inactive().equals(\old(machine.get_inactive().difference((new BRelation<Integer,Integer>(new Pair<Integer,Integer>(u2,u1)).intersection(machine.get_inactive()))))) &&  machine.get_screen().equals(\old((machine.get_screen().override(new BRelation<Integer,BRelation<Integer,BRelation<Integer,Integer>>>(new Pair<Integer,BRelation<Integer,BRelation<Integer,Integer>>>(u1,(machine.get_screen().apply(u1).override(new BRelation<Integer,BRelation<Integer,Integer>>(new Pair<Integer,BRelation<Integer,Integer>>(u2,new BRelation<Integer,Integer>(new Pair<Integer,Integer>(k1,c))))))),new Pair<Integer,BRelation<Integer,BRelation<Integer,Integer>>>(u2,(machine.get_screen().apply(u2).override(new BRelation<Integer,BRelation<Integer,Integer>>(new Pair<Integer,BRelation<Integer,Integer>>(u1,new BRelation<Integer,Integer>(new Pair<Integer,Integer>(k2,c)))))))))))) &&  machine.get_csize() == \old(new Integer(machine.get_csize() + 1)) &&  machine.get_contents().equals(\old((machine.get_contents().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(new Integer(machine.get_csize() + 1),c)))))); 
	 also
		requires !guard_chatting_first_time(c,u1,u2,k1,k2);
		assignable \nothing;
		ensures true; */
	public void run_chatting_first_time( Integer c, Integer u1, Integer u2, Integer k1, Integer k2){
		if(guard_chatting_first_time(c,u1,u2,k1,k2)) {
			BSet<Integer> content_tmp = machine.get_content();
			BRelation<Integer,Integer> chat_tmp = machine.get_chat();
			BRelation<Integer,BRelation<Integer,BSet<Integer>>> chatcontent_tmp = machine.get_chatcontent();
			BRelation<Integer,Integer> toread_tmp = machine.get_toread();
			BRelation<Integer,Integer> inactive_tmp = machine.get_inactive();
			BRelation<Integer,BRelation<Integer,BRelation<Integer,Integer>>> screen_tmp = machine.get_screen();
			Integer csize_tmp = machine.get_csize();
			BRelation<Integer,Integer> contents_tmp = machine.get_contents();

			machine.set_content((content_tmp.union(new BSet<Integer>(c))));
			machine.set_chat((chat_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(u2,u1)))));
			machine.set_chatcontent((chatcontent_tmp.override(new BRelation<Integer,BRelation<Integer,BSet<Integer>>>(new Pair<Integer,BRelation<Integer,BSet<Integer>>>(u1,(chatcontent_tmp.apply(u1).union(new BRelation<Integer,BSet<Integer>>(new Pair<Integer,BSet<Integer>>(c,new BSet<Integer>(u2))))))))));
			machine.set_toread((toread_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(u2,u1)).difference(machine.get_active()).union((new BRelation<Integer,Integer>(new Pair<Integer,Integer>(u2,u1)).intersection(inactive_tmp))))));
			machine.set_inactive(inactive_tmp.difference((new BRelation<Integer,Integer>(new Pair<Integer,Integer>(u2,u1)).intersection(inactive_tmp))));
			machine.set_screen((screen_tmp.override(new BRelation<Integer,BRelation<Integer,BRelation<Integer,Integer>>>(new Pair<Integer,BRelation<Integer,BRelation<Integer,Integer>>>(u1,(screen_tmp.apply(u1).override(new BRelation<Integer,BRelation<Integer,Integer>>(new Pair<Integer,BRelation<Integer,Integer>>(u2,new BRelation<Integer,Integer>(new Pair<Integer,Integer>(k1,c))))))),new Pair<Integer,BRelation<Integer,BRelation<Integer,Integer>>>(u2,(screen_tmp.apply(u2).override(new BRelation<Integer,BRelation<Integer,Integer>>(new Pair<Integer,BRelation<Integer,Integer>>(u1,new BRelation<Integer,Integer>(new Pair<Integer,Integer>(k2,c)))))))))));
			machine.set_csize(new Integer(csize_tmp + 1));
			machine.set_contents((contents_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(new Integer(csize_tmp + 1),c)))));

			System.out.println("chatting_first_time executed c: " + c + " u1: " + u1 + " u2: " + u2 + " k1: " + k1 + " k2: " + k2 + " ");
		}
	}

}
