package com.company.dsii.whatsapp.EventB.whatsapp_thread;

import com.company.dsii.whatsapp.EventB.eventb_prelude.*;
import com.company.dsii.whatsapp.EventB.Util.Utilities;

public class forward_one{
	/*@ spec_public */ private machine2 machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public forward_one(machine2 m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_user().has(u) && us.isSubset(machine.get_user()) && (machine.get_muted().image(new BSet<Integer>(u)).intersection(us)).equals(BSet.EMPTY) && (machine.get_muted().image(us).intersection(new BSet<Integer>(u))).equals(BSet.EMPTY) && machine.get_content().has(c) && machine.get_chatcontent().domain().has(u) && us.isSubset(machine.get_chat().image(new BSet<Integer>(u))) && machine.get_screen().domain().has(u) && us.isSubset(machine.get_screen().apply(u).domain()) && ks.isSubset(NAT.instance) && new Integer(ks.size()).equals(new Integer(us.size()))); */
	public /*@ pure */ boolean guard_forward_one( Integer c, Integer u, BSet<Integer> us, BSet<Integer> ks) {
		return (machine.get_user().has(u) && us.isSubset(machine.get_user()) && (machine.get_muted().image(new BSet<Integer>(u)).intersection(us)).equals(BSet.EMPTY) && (machine.get_muted().image(us).intersection(new BSet<Integer>(u))).equals(BSet.EMPTY) && machine.get_content().has(c) && machine.get_chatcontent().domain().has(u) && us.isSubset(machine.get_chat().image(new BSet<Integer>(u))) && machine.get_screen().domain().has(u) && us.isSubset(machine.get_screen().apply(u).domain()) && ks.isSubset(NAT.instance) && new Integer(ks.size()).equals(new Integer(us.size())));
	}

	/*@ public normal_behavior
		requires guard_forward_one(c,u,us,ks);
		assignable machine.chatcontent, machine.content, machine.chat, machine.toread, machine.inactive, machine.screen;
		ensures guard_forward_one(c,u,us,ks) &&  machine.get_chatcontent().equals(\old((machine.get_chatcontent().override(new BRelation<Integer,BRelation<Integer,BSet<Integer>>>(new Pair<Integer,BRelation<Integer,BSet<Integer>>>(u,(machine.get_chatcontent().apply(u).union(new BRelation<Integer,BSet<Integer>>(new Pair<Integer,BSet<Integer>>(c,us)))))))))) &&  machine.get_content().equals(\old((machine.get_content().union(new BSet<Integer>(c))))) &&  machine.get_chat().equals(\old((machine.get_chat().union(BRelation.cross(us,new BSet<Integer>(u)))))) &&  machine.get_toread().equals(\old((machine.get_toread().union((BRelation.cross(us,new BSet<Integer>(u)).union(BRelation.cross(new BSet<Integer>(u),us))).difference(machine.get_active()).union(((BRelation.cross(us,new BSet<Integer>(u)).union(BRelation.cross(new BSet<Integer>(u),us))).intersection(machine.get_inactive()))))))) &&  machine.get_inactive().equals(\old(machine.get_inactive().difference(((BRelation.cross(us,new BSet<Integer>(u)).union(BRelation.cross(new BSet<Integer>(u),us))).intersection(machine.get_inactive()))))) &&  machine.get_screen().equals(\old((machine.get_screen().override(new BRelation<Integer,BRelation<Integer,BRelation<Integer,Integer>>>(new Pair<Integer,BRelation<Integer,BRelation<Integer,Integer>>>(u,(machine.get_screen().apply(u).override(new Best<Integer>(new JMLObjectSet {Integer u2Integer k | (\exists Integer e; (us.has(null) && !machine.get_screen().apply(u).apply(null).domain().has(k)); e.equals(new Pair<BRelation<Integer,Integer>,ERROR>(null,(machine.get_screen().apply(u).apply(null).override(new BRelation<Integer,ERROR>(new Pair<Integer,ERROR>(k,c)))))))}))))))))); 
	 also
		requires !guard_forward_one(c,u,us,ks);
		assignable \nothing;
		ensures true; */
	public void run_forward_one( Integer c, Integer u, BSet<Integer> us, BSet<Integer> ks){
		if(guard_forward_one(c,u,us,ks)) {
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

			System.out.println("forward_one executed c: " + c + " u: " + u + " us: " + us + " ks: " + ks + " ");
		}
	}

}
