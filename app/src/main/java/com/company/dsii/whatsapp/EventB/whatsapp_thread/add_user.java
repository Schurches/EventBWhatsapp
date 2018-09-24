package com.company.dsii.whatsapp.EventB.whatsapp_thread;

import com.company.dsii.whatsapp.EventB.eventb_prelude.*;
import com.company.dsii.whatsapp.EventB.Util.Utilities;

public class add_user{
	/*@ spec_public */ private machine2 machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public add_user(machine2 m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> machine.USER.difference(machine.get_user()).has(u); */
	public /*@ pure */ boolean guard_add_user( Integer u) {
		return machine.USER.difference(machine.get_user()).has(u);
	}

	/*@ public normal_behavior
		requires guard_add_user(u);
		assignable machine.user, machine.chatcontent, machine.screen;
		ensures guard_add_user(u) &&  machine.get_user().equals(\old((machine.get_user().union(new BSet<Integer>(u))))) &&  machine.get_chatcontent().equals(\old((machine.get_chatcontent().override(new BRelation<Integer,ERROR>(new Pair<Integer,ERROR>(u,BRelation.EMPTY)))))) &&  machine.get_screen().equals(\old((machine.get_screen().override(new BRelation<Integer,BSet<Integer>>(new Pair<Integer,BSet<Integer>>(u,BRelation.cross(machine.get_user(),new BSet<ERROR>(BRelation.EMPTY)))))))); 
	 also
		requires !guard_add_user(u);
		assignable \nothing;
		ensures true; */
	public void run_add_user( Integer u){
		if(guard_add_user(u)) {
			BSet<Integer> user_tmp = machine.get_user();
			BRelation<Integer,BRelation<Integer,BSet<Integer>>> chatcontent_tmp = machine.get_chatcontent();
			BRelation<Integer,BRelation<Integer,BRelation<Integer,Integer>>> screen_tmp = machine.get_screen();
			machine.set_user((user_tmp.union(new BSet<Integer>(u))));
machine.set_chatcontent((chatcontent_tmp.override(new BRelation<Integer,BRelation<Integer,BSet<Integer>>>(new Pair<Integer,BRelation<Integer,BSet<Integer>>>(u,BRelation.EMPTY)))));
machine.set_screen((screen_tmp.override(new BRelation<Integer,BRelation<Integer,BRelation<Integer,Integer>>>(new Pair<Integer,BRelation<Integer,BRelation<Integer,Integer>>>(u,BRelation.cross(user_tmp,BSet.EMPTY))))));
			System.out.println("add_user executed u: " + u + " ");
		}
	}

}
