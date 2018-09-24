package com.company.dsii.whatsapp.EventB.whatsapp_thread;

import com.company.dsii.whatsapp.EventB.eventb_prelude.*;
import com.company.dsii.whatsapp.EventB.Util.Utilities;
import java.util.Random;

public class Test_machine2{


	static Random rnd = new Random();
	static Integer max_size_BSet = 10;
	Integer min_integer = Utilities.min_integer;
	Integer max_integer = Utilities.max_integer;

	public Integer GenerateRandomInteger(){
		BSet<Integer> S =  new BSet<Integer>(
				new Enumerated(min_integer, max_integer)
				);
		/** User defined code that reflects axioms and theorems: Begin **/

		/** User defined code that reflects axioms and theorems: End **/

		return (Integer) S.toArray()[rnd.nextInt(S.size())];
	}

	public boolean GenerateRandomBoolean(){
		boolean res = (Boolean) BOOL.instance.toArray()[rnd.nextInt(2)];

		/** User defined code that reflects axioms and theorems: Begin **/

		/** User defined code that reflects axioms and theorems: End **/

		return res;
	}


	public BSet<Integer> GenerateRandomIntegerBSet(){
		int size = rnd.nextInt(max_size_BSet);
		BSet<Integer> S = new BSet<Integer>();
		while (S.size() != size){
			S.add(GenerateRandomInteger());
		}

		/** User defined code that reflects axioms and theorems: Begin **/

		/** User defined code that reflects axioms and theorems: End **/

		return S;
	}

	public BSet<Boolean> GenerateRandomBooleanBSet(){
		int size = rnd.nextInt(2);
		BSet<Boolean> res = new BSet<Boolean>();
		if (size == 0){
			res = new BSet<Boolean>(GenerateRandomBoolean());
		}else{
			res = new BSet<Boolean>(true,false);
		}

		/** User defined code that reflects axioms and theorems: Begin **/

		/** User defined code that reflects axioms and theorems: End **/

		return res;
	}

	public BRelation<Integer,Integer> GenerateRandomBRelation(){
		BRelation<Integer,Integer> res = new BRelation<Integer,Integer>();
		int size = rnd.nextInt(max_size_BSet);
		while (res.size() != size){
			res.add(
					new Pair<Integer, Integer>(GenerateRandomInteger(), GenerateRandomInteger()));
		}
		/** User defined code that reflects axioms and theorems: Begin **/

		/** User defined code that reflects axioms and theorems: End **/

		return res;
	}

/*	public static void main(String[] args){
		Test_machine2 test = new Test_machine2();

	//	/** User defined code that reflects axioms and theorems: Begin **/
	//	/** User defined code that reflects axioms and theorems: End **/


		/*machine2 machine = new machine2();
		int n = 15; //the number of events in the machine
		//Init values for parameters in event: add_user
		Integer u = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: add_content
		Integer c = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: create_chat_session
		Integer u1 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer u2 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: select_chat
		Integer u1 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer u2 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: chatting_first_time
		Integer c = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer u1 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer u2 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer k1 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer k2 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: chatting
		Integer c = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer u1 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer u2 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer k1 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer k2 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: delete_content
		Integer c = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer u1 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer u2 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer i = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer k = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: remove_content
		Integer c = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer u1 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer u2 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: mute_chat
		Integer u1 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer u2 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: unmute_chat
		Integer u1 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer u2 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: broadcast
		Integer c = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer u = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		BSet<Integer> us = Utilities.someSet(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: forward_one
		Integer c = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer u = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		BSet<Integer> us = Utilities.someSet(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		BSet<Integer> ks = Utilities.someSet(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: forward
		Integer c = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer u = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		BSet<Integer> us = Utilities.someSet(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer k = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: unselect_chat
		Integer u1 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer u2 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));*/
/*
		while (true){
			switch (rnd.nextInt(n)){
			case 0: if (machine.evt_add_user.guard_add_user(u))
				machine.evt_add_user.run_add_user(u); break;
			case 1: if (machine.evt_add_content.guard_add_content(c))
				machine.evt_add_content.run_add_content(c); break;
			case 2: if (machine.evt_create_chat_session.guard_create_chat_session(u1,u2))
				machine.evt_create_chat_session.run_create_chat_session(u1,u2); break;
			case 3: if (machine.evt_select_chat.guard_select_chat(u1,u2))
				machine.evt_select_chat.run_select_chat(u1,u2); break;
			case 4: if (machine.evt_chatting_first_time.guard_chatting_first_time(c,u1,u2,k1,k2))
				machine.evt_chatting_first_time.run_chatting_first_time(c,u1,u2,k1,k2); break;
			case 5: if (machine.evt_chatting.guard_chatting(c,u1,u2,k1,k2))
				machine.evt_chatting.run_chatting(c,u1,u2,k1,k2); break;
			case 6: if (machine.evt_delete_content.guard_delete_content(c,u1,u2,i,k))
				machine.evt_delete_content.run_delete_content(c,u1,u2,i,k); break;
			case 7: if (machine.evt_remove_content.guard_remove_content(c,u1,u2))
				machine.evt_remove_content.run_remove_content(c,u1,u2); break;
			case 8: if (machine.evt_delete_chat_session.guard_delete_chat_session())
				machine.evt_delete_chat_session.run_delete_chat_session(); break;
			case 9: if (machine.evt_mute_chat.guard_mute_chat(u1,u2))
				machine.evt_mute_chat.run_mute_chat(u1,u2); break;
			case 10: if (machine.evt_unmute_chat.guard_unmute_chat(u1,u2))
				machine.evt_unmute_chat.run_unmute_chat(u1,u2); break;
			case 11: if (machine.evt_broadcast.guard_broadcast(c,u,us))
				machine.evt_broadcast.run_broadcast(c,u,us); break;
			case 12: if (machine.evt_forward_one.guard_forward_one(c,u,us,ks))
				machine.evt_forward_one.run_forward_one(c,u,us,ks); break;
			case 13: if (machine.evt_forward.guard_forward(c,u,us,k))
				machine.evt_forward.run_forward(c,u,us,k); break;
			case 14: if (machine.evt_unselect_chat.guard_unselect_chat(u1,u2))
				machine.evt_unselect_chat.run_unselect_chat(u1,u2); break;
			}
			u = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			c = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			u1 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			u2 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			u1 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			u2 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			c = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			u1 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			u2 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			k1 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			k2 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			c = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			u1 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			u2 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			k1 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			k2 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			c = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			u1 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			u2 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			i = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			k = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			c = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			u1 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			u2 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			u1 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			u2 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			u1 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			u2 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			c = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			u = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			us = Utilities.someSet(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			c = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			u = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			us = Utilities.someSet(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			ks = Utilities.someSet(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			c = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			u = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			us = Utilities.someSet(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			k = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			u1 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			u2 = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		}
	}
	*/

}
