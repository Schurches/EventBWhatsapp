package com.company.dsii.whatsapp.EventB.whatsapp_thread;

import com.company.dsii.whatsapp.EventB.eventb_prelude.*;
import com.company.dsii.whatsapp.EventB.Util.Utilities;

public class create_chat_session{
	/*@ spec_public */ private machine2 machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public create_chat_session(machine2 m) {
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
		assignable machine.chat, machine.active, machine.inactive;
		ensures guard_create_chat_session(u1,u2) &&  machine.get_chat().equals(\old((machine.get_chat().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(u1,u2)))))) &&  machine.get_active().equals(\old((machine.get_active().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(u1,u2)))))) &&  machine.get_inactive().equals(\old((machine.get_inactive().union(machine.get_active().restrictDomainTo(new BSet<Integer>(u1)))))); 
	 also
		requires !guard_create_chat_session(u1,u2);
		assignable \nothing;
		ensures true; */
	public void run_create_chat_session( Integer u1, Integer u2){
		if(guard_create_chat_session(u1,u2)) {
			BRelation<Integer,Integer> chat_tmp = machine.get_chat();
			BRelation<Integer,Integer> active_tmp = machine.get_active();
			BRelation<Integer,Integer> inactive_tmp = machine.get_inactive();

			machine.set_chat((chat_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(u1,u2)))));
			machine.set_active((active_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(u1,u2)))));
			machine.set_inactive((inactive_tmp.union(active_tmp.restrictDomainTo(new BSet<Integer>(u1)))));

			System.out.println("create_chat_session executed u1: " + u1 + " u2: " + u2 + " ");
		}
	}

}
