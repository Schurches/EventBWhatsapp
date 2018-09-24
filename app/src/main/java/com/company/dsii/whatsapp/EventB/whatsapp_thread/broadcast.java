package com.company.dsii.whatsapp.EventB.whatsapp_thread;

import com.company.dsii.whatsapp.EventB.eventb_prelude.*;
import com.company.dsii.whatsapp.EventB.Util.Utilities;

public class broadcast{
	/*@ spec_public */ private machine2 machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public broadcast(machine2 m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_user().has(u) && us.isSubset(machine.get_user()) && (machine.get_muted().image(new BSet<Integer>(u)).intersection(us)).equals(BSet.EMPTY) && (machine.get_muted().image(us).intersection(new BSet<Integer>(u))).equals(BSet.EMPTY) && machine.get_content().has(c) && machine.get_chatcontent().domain().has(u) && !us.has(u)); */
	public /*@ pure */ boolean guard_broadcast( Integer c, Integer u, BSet<Integer> us) {
		return (machine.get_user().has(u) && us.isSubset(machine.get_user()) && (machine.get_muted().image(new BSet<Integer>(u)).intersection(us)).equals(BSet.EMPTY) && (machine.get_muted().image(us).intersection(new BSet<Integer>(u))).equals(BSet.EMPTY) && machine.get_content().has(c) && machine.get_chatcontent().domain().has(u) && !us.has(u));
	}

	/*@ public normal_behavior
		requires guard_broadcast(c,u,us);
		assignable machine.chatcontent, machine.content, machine.chat, machine.toread, machine.inactive;
		ensures guard_broadcast(c,u,us) &&  machine.get_chatcontent().equals(\old((machine.get_chatcontent().override(new BRelation<Integer,BSet<Integer>>(new Pair<Integer,BSet<Integer>>(u,BRelation.cross(new BSet<Integer>(c),new BSet<BSet<Integer>>(us)))).override(BRelation.cross(us,new BSet<BSet<Integer>>(BRelation.cross(new BSet<Integer>(c),new BSet<BSet<Integer>>(new BSet<Integer>(u)))))))))) &&  machine.get_content().equals(\old((machine.get_content().union(new BSet<Integer>(c))))) &&  machine.get_chat().equals(\old((machine.get_chat().union(BRelation.cross(new BSet<Integer>(u),us).union(BRelation.cross(us,new BSet<Integer>(u))))))) &&  machine.get_toread().equals(\old((machine.get_toread().union((BRelation.cross(us,new BSet<Integer>(u)).union(BRelation.cross(new BSet<Integer>(u),us))).difference(machine.get_active()).union(((BRelation.cross(us,new BSet<Integer>(u)).union(BRelation.cross(new BSet<Integer>(u),us))).intersection(machine.get_inactive()))))))) &&  machine.get_inactive().equals(\old(machine.get_inactive().difference(((BRelation.cross(us,new BSet<Integer>(u)).union(BRelation.cross(new BSet<Integer>(u),us))).intersection(machine.get_inactive()))))); 
	 also
		requires !guard_broadcast(c,u,us);
		assignable \nothing;
		ensures true; */
	public void run_broadcast( Integer c, Integer u, BSet<Integer> us){
		if(guard_broadcast(c,u,us)) {
			BRelation<Integer,BRelation<Integer,BSet<Integer>>> chatcontent_tmp = machine.get_chatcontent();
			BSet<Integer> content_tmp = machine.get_content();
			BRelation<Integer,Integer> chat_tmp = machine.get_chat();
			BRelation<Integer,Integer> toread_tmp = machine.get_toread();
			BRelation<Integer,Integer> inactive_tmp = machine.get_inactive();
			machine.set_chatcontent((chatcontent_tmp.override(new BRelation<Integer,BRelation<Integer,BSet<Integer>>>(new Pair<Integer,BRelation<Integer,BSet<Integer>>>(u,(chatcontent_tmp.apply(u).union(new BRelation<Integer,BSet<Integer>>(new Pair<Integer,BSet<Integer>>(c,us)))))))));	
			machine.set_toread((toread_tmp.union((BRelation.cross(us,new BSet<Integer>(u)).union(BRelation.cross(new BSet<Integer>(u),us))).difference(machine.get_active()).union(((BRelation.cross(us,new BSet<Integer>(u)).union(BRelation.cross(new BSet<Integer>(u),us))).intersection(inactive_tmp))))));
			machine.set_inactive(inactive_tmp.difference(((BRelation.cross(us,new BSet<Integer>(u)).union(BRelation.cross(new BSet<Integer>(u),us))).intersection(inactive_tmp))));

			System.out.println("broadcast executed c: " + c + " u: " + u + " us: " + us + " ");
		}
	}

}
