package com.company.dsii.whatsapp.EventB.whatsapp_thread;

import com.company.dsii.whatsapp.EventB.eventb_prelude.*;
import com.company.dsii.whatsapp.EventB.Util.Utilities;

public class delete_content{
	/*@ spec_public */ private machine2 machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public delete_content(machine2 m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_user().has(u1) && machine.get_user().has(u2) && machine.get_active().has(new Pair<Integer,Integer>(u1,u2)) && machine.get_chatcontent().domain().has(u1) && machine.get_chatcontent().apply(u1).domain().has(c) && machine.get_chatcontent().apply(u1).apply(c).has(u2) && machine.get_contents().has(new Pair<Integer,Integer>(i,c)) && machine.get_screen().domain().has(u1) && machine.get_screen().apply(u1).domain().has(u2) && machine.get_screen().apply(u1).apply(u2).has(new Pair<Integer,Integer>(k,c))); */
	public /*@ pure */ boolean guard_delete_content( Integer c, Integer u1, Integer u2, Integer i, Integer k) {
		return (machine.get_user().has(u1) && machine.get_user().has(u2) && machine.get_active().has(new Pair<Integer,Integer>(u1,u2)) && machine.get_chatcontent().domain().has(u1) && machine.get_chatcontent().apply(u1).domain().has(c) && machine.get_chatcontent().apply(u1).apply(c).has(u2) && machine.get_contents().has(new Pair<Integer,Integer>(i,c)) && machine.get_screen().domain().has(u1) && machine.get_screen().apply(u1).domain().has(u2) && machine.get_screen().apply(u1).apply(u2).has(new Pair<Integer,Integer>(k,c)));
	}

	/*@ public normal_behavior
		requires guard_delete_content(c,u1,u2,i,k);
		assignable machine.chatcontent, machine.screen;
		ensures guard_delete_content(c,u1,u2,i,k) &&  machine.get_chatcontent().equals(\old((machine.get_chatcontent().override(new BRelation<Integer,BRelation<Integer,BSet<Integer>>>(new Pair<Integer,BRelation<Integer,BSet<Integer>>>(u1,(machine.get_chatcontent().apply(u1).override(new BRelation<Integer,BSet<Integer>>(new Pair<Integer,BSet<Integer>>(c,machine.get_chatcontent().apply(u1).apply(c).difference(new BSet<Integer>(u2)))))))))))) &&  machine.get_screen().equals(\old((machine.get_screen().override(new BRelation<Integer,BRelation<Integer,BRelation<Integer,Integer>>>(new Pair<Integer,BRelation<Integer,BRelation<Integer,Integer>>>(u1,(machine.get_screen().apply(u1).override(new BRelation<Integer,BRelation<Integer,Integer>>(new Pair<Integer,BRelation<Integer,Integer>>(u2,machine.get_screen().apply(u1).apply(u2).domainSubtraction(new BSet<Integer>(k)))))))))))); 
	 also
		requires !guard_delete_content(c,u1,u2,i,k);
		assignable \nothing;
		ensures true; */
	public void run_delete_content( Integer c, Integer u1, Integer u2, Integer i, Integer k){
		if(guard_delete_content(c,u1,u2,i,k)) {
			BRelation<Integer,BRelation<Integer,BSet<Integer>>> chatcontent_tmp = machine.get_chatcontent();
			BRelation<Integer,BRelation<Integer,BRelation<Integer,Integer>>> screen_tmp = machine.get_screen();

			machine.set_chatcontent((chatcontent_tmp.override(new BRelation<Integer,BRelation<Integer,BSet<Integer>>>(new Pair<Integer,BRelation<Integer,BSet<Integer>>>(u1,(chatcontent_tmp.apply(u1).override(new BRelation<Integer,BSet<Integer>>(new Pair<Integer,BSet<Integer>>(c,chatcontent_tmp.apply(u1).apply(c).difference(new BSet<Integer>(u2)))))))))));
			machine.set_screen((screen_tmp.override(new BRelation<Integer,BRelation<Integer,BRelation<Integer,Integer>>>(new Pair<Integer,BRelation<Integer,BRelation<Integer,Integer>>>(u1,(screen_tmp.apply(u1).override(new BRelation<Integer,BRelation<Integer,Integer>>(new Pair<Integer,BRelation<Integer,Integer>>(u2,screen_tmp.apply(u1).apply(u2).domainSubtraction(new BSet<Integer>(k)))))))))));

			System.out.println("delete_content executed c: " + c + " u1: " + u1 + " u2: " + u2 + " i: " + i + " k: " + k + " ");
		}
	}

}
