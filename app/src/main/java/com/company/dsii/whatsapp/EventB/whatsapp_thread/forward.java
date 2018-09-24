package com.company.dsii.whatsapp.EventB.whatsapp_thread;

import com.company.dsii.whatsapp.EventB.eventb_prelude.*;
import com.company.dsii.whatsapp.EventB.Util.Utilities;

public class forward{
	/*@ spec_public */ private machine2 machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public forward(machine2 m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_user().has(u) && us.isSubset(machine.get_user()) && (machine.get_muted().image(new BSet<Integer>(u)).intersection(us)).equals(BSet.EMPTY) && (machine.get_muted().image(us).intersection(new BSet<Integer>(u))).equals(BSet.EMPTY) && machine.get_content().has(c) && machine.get_chatcontent().domain().has(u) && us.isSubset(machine.get_chat().image(new BSet<Integer>(u))) && machine.get_screen().domain().has(u) && us.isSubset(machine.get_screen().apply(u).domain()) && NAT.instance.has(k) &&  (\forall Integer u2;  (\forall Integer i;((us.has(u2) && machine.get_screen().apply(u).apply(u2).domain().has(i)) ==> ((k).compareTo(i) > 0))))); */
	public /*@ pure */ boolean guard_forward( Integer c, Integer u, BSet<Integer> us, Integer k) {
		return (machine.get_user().has(u) && us.isSubset(machine.get_user()) && (machine.get_muted().image(new BSet<Integer>(u)).intersection(us)).equals(BSet.EMPTY) && (machine.get_muted().image(us).intersection(new BSet<Integer>(u))).equals(BSet.EMPTY) && machine.get_content().has(c) && machine.get_chatcontent().domain().has(u) && us.isSubset(machine.get_chat().image(new BSet<Integer>(u))) && machine.get_screen().domain().has(u) && us.isSubset(machine.get_screen().apply(u).domain()) && true);
	}

	/*@ public normal_behavior
		requires guard_forward(c,u,us,k);
		assignable machine.chatcontent, machine.content, machine.chat, machine.toread, machine.inactive, machine.screen;
		ensures guard_forward(c,u,us,k) &&  machine.get_chatcontent().equals(\old((machine.get_chatcontent().override(new BRelation<Integer,BRelation<Integer,BSet<Integer>>>(new Pair<Integer,BRelation<Integer,BSet<Integer>>>(u,(machine.get_chatcontent().apply(u).union(new BRelation<Integer,BSet<Integer>>(new Pair<Integer,BSet<Integer>>(c,us)))))))))) &&  machine.get_content().equals(\old((machine.get_content().union(new BSet<Integer>(c))))) &&  machine.get_chat().equals(\old((machine.get_chat().union(BRelation.cross(us,new BSet<Integer>(u)))))) &&  machine.get_toread().equals(\old((machine.get_toread().union((BRelation.cross(us,new BSet<Integer>(u)).union(BRelation.cross(new BSet<Integer>(u),us))).difference(machine.get_active()).union(((BRelation.cross(us,new BSet<Integer>(u)).union(BRelation.cross(new BSet<Integer>(u),us))).intersection(machine.get_inactive()))))))) &&  machine.get_inactive().equals(\old(machine.get_inactive().difference(((BRelation.cross(us,new BSet<Integer>(u)).union(BRelation.cross(new BSet<Integer>(u),us))).intersection(machine.get_inactive()))))) &&  machine.get_screen().equals(\old((machine.get_screen().override(new BRelation<Integer,BRelation<Integer,BRelation<Integer,Integer>>>(new Pair<Integer,BRelation<Integer,BRelation<Integer,Integer>>>(u,(machine.get_screen().apply(u).override(new Best<Integer>(new JMLObjectSet {Integer u2 | (\exists Integer e; (us.has(u2)); e.equals(new Pair<BRelation<Integer,Integer>,ERROR>(u2,(machine.get_screen().apply(u).apply(u2).override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(k,c)))))))}))))))))); 
	 also
		requires !guard_forward(c,u,us,k);
		assignable \nothing;
		ensures true; */
	public void run_forward( Integer c, Integer u, BSet<Integer> us, Integer k){
		if(guard_forward(c,u,us,k)) {
			BRelation<Integer,BRelation<Integer,BSet<Integer>>> chatcontent_tmp = machine.get_chatcontent();
			BSet<Integer> content_tmp = machine.get_content();
			BRelation<Integer,Integer> chat_tmp = machine.get_chat();
			BRelation<Integer,Integer> toread_tmp = machine.get_toread();
			BRelation<Integer,Integer> inactive_tmp = machine.get_inactive();
			BRelation<Integer,BRelation<Integer,BRelation<Integer,Integer>>> screen_tmp = machine.get_screen();

			machine.set_chatcontent((chatcontent_tmp.override(new BRelation<Integer,BRelation<Integer,BSet<Integer>>>(new Pair<Integer,BRelation<Integer,BSet<Integer>>>(u,(chatcontent_tmp.apply(u).union(new BRelation<Integer,BSet<Integer>>(new Pair<Integer,BSet<Integer>>(c,us)))))))));
			machine.set_content((content_tmp.union(new BSet<Integer>(c))));
			machine.set_chat((chat_tmp.union(BRelation.cross(us,new BSet<Integer>(u)))));
			machine.set_toread((toread_tmp.union((BRelation.cross(us,new BSet<Integer>(u)).union(BRelation.cross(new BSet<Integer>(u),us))).difference(machine.get_active()).union(((BRelation.cross(us,new BSet<Integer>(u)).union(BRelation.cross(new BSet<Integer>(u),us))).intersection(inactive_tmp))))));
			machine.set_inactive(inactive_tmp.difference(((BRelation.cross(us,new BSet<Integer>(u)).union(BRelation.cross(new BSet<Integer>(u),us))).intersection(inactive_tmp))));
			machine.set_screen(null); // Set Comprehension: feature not supported by EventB2Java

			System.out.println("forward executed c: " + c + " u: " + u + " us: " + us + " k: " + k + " ");
		}
	}

}
