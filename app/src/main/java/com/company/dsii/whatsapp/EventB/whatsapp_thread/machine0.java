package com.company.dsii.whatsapp.EventB.whatsapp_thread;

import eventb_prelude.*;
import Util.*;
//@ model import org.jmlspecs.models.JMLObjectSet;
import whatsapp_broadcast.broadcast;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class machine0{
	private int n_events = 13;
	private static final Integer max_integer = Utilities.max_integer;
	private static final Integer min_integer = Utilities.min_integer;
	private Thread[] events;
	public Lock lock = new ReentrantLock(true);


	/******Set definitions******/
	//@ public static constraint CONTENT.equals(\old(CONTENT)); 
	public static final BSet<Integer> CONTENT = new Enumerated(min_integer,max_integer);

	//@ public static constraint USER.equals(\old(USER)); 
	public static final BSet<Integer> USER = new Enumerated(min_integer,max_integer);


	/******Constant definitions******/


	/******Axiom definitions******/


	/******Variable definitions******/
	/*@ spec_public */ private BRelation<Integer,Integer> active;

	/*@ spec_public */ private BRelation<Integer,Integer> chat;

	/*@ spec_public */ private BRelation<Integer,BRelation<Integer,BSet<Integer>>> chatcontent;

	/*@ spec_public */ private BSet<Integer> content;

	/*@ spec_public */ private BRelation<Integer,Integer> muted;

	/*@ spec_public */ private BSet<Integer> user;




	/******Invariant definition******/
	/*@ public invariant
		user.isSubset(USER) &&
		content.isSubset(CONTENT) &&
		 chat.domain().isSubset(user) && chat.range().isSubset(user) && BRelation.cross(user,user).has(chat) &&
		 chatcontent.domain().isSubset(user) && chatcontent.range().isSubset(BRelation.cross(content,((user).pow()))) && chatcontent.isaFunction() && BRelation.cross(user,BRelation.cross(content,((user).pow()))).has(chatcontent) &&
		 active.domain().isSubset(user) && active.range().isSubset(user) && active.isaFunction() && BRelation.cross(user,user).has(active) &&
		 muted.domain().isSubset(user) && muted.range().isSubset(user) && BRelation.cross(user,user).has(muted) &&
		active.isSubset(chat) &&
		muted.isSubset(chat) &&
		(muted.intersection(active)).equals(BSet.EMPTY); */


	/******Getter and Mutator methods definition******/
	/*@ public normal_behavior
	    requires true;
	    assignable \nothing;
	    ensures \result == this.chatcontent;*/
	public /*@ pure */ BRelation<Integer,BRelation<Integer,BSet<Integer>>> get_chatcontent(){
		return this.chatcontent;
	}

	/*@ public normal_behavior
	    requires true;
	    assignable this.chatcontent;
	    ensures this.chatcontent == chatcontent;*/
	public void set_chatcontent(BRelation<Integer,BRelation<Integer,BSet<Integer>>> chatcontent){
		this.chatcontent = chatcontent;
	}

	/*@ public normal_behavior
	    requires true;
	    assignable \nothing;
	    ensures \result == this.chat;*/
	public /*@ pure */ BRelation<Integer,Integer> get_chat(){
		return this.chat;
	}

	/*@ public normal_behavior
	    requires true;
	    assignable this.chat;
	    ensures this.chat == chat;*/
	public void set_chat(BRelation<Integer,Integer> chat){
		this.chat = chat;
	}

	/*@ public normal_behavior
	    requires true;
	    assignable \nothing;
	    ensures \result == this.active;*/
	public /*@ pure */ BRelation<Integer,Integer> get_active(){
		return this.active;
	}

	/*@ public normal_behavior
	    requires true;
	    assignable this.active;
	    ensures this.active == active;*/
	public void set_active(BRelation<Integer,Integer> active){
		this.active = active;
	}

	/*@ public normal_behavior
	    requires true;
	    assignable \nothing;
	    ensures \result == this.muted;*/
	public /*@ pure */ BRelation<Integer,Integer> get_muted(){
		return this.muted;
	}

	/*@ public normal_behavior
	    requires true;
	    assignable this.muted;
	    ensures this.muted == muted;*/
	public void set_muted(BRelation<Integer,Integer> muted){
		this.muted = muted;
	}

	/*@ public normal_behavior
	    requires true;
	    assignable \nothing;
	    ensures \result == this.user;*/
	public /*@ pure */ BSet<Integer> get_user(){
		return this.user;
	}

	/*@ public normal_behavior
	    requires true;
	    assignable this.user;
	    ensures this.user == user;*/
	public void set_user(BSet<Integer> user){
		this.user = user;
	}

	/*@ public normal_behavior
	    requires true;
	    assignable \nothing;
	    ensures \result == this.content;*/
	public /*@ pure */ BSet<Integer> get_content(){
		return this.content;
	}

	/*@ public normal_behavior
	    requires true;
	    assignable this.content;
	    ensures this.content == content;*/
	public void set_content(BSet<Integer> content){
		this.content = content;
	}



	/*@ public normal_behavior
	    requires true;
	    assignable \everything;
	    ensures
		user.isEmpty() &&
		content.isEmpty() &&
		chat.isEmpty() &&
		active.isEmpty() &&
		chatcontent.isEmpty() &&
		muted.isEmpty();*/
	public machine0(){
		user = new BSet<Integer>();
		content = new BSet<Integer>();
		chat = new BRelation<Integer,Integer>();
		active = new BRelation<Integer,Integer>();
		chatcontent = new BRelation<Integer,BRelation<Integer,BSet<Integer>>>();
		muted = new BRelation<Integer,Integer>();

		events = new Thread[n_events];
		events[0] = new broadcast(this);
		events[1] = new add_content(this);
		events[2] = new unselect_chat(this);
		events[3] = new forward(this);
		events[4] = new delete_content(this);
		events[5] = new create_chat_session(this);
		events[6] = new delete_chat_session(this);
		events[7] = new unmute_chat(this);
		events[8] = new select_chat(this);
		events[9] = new mute_chat(this);
		events[10] = new chatting(this);
		events[11] = new remove_content(this);
		events[12] = new add_user(this);

		for (int i = 0; i < n_events;i++){
			events[i].start();
		}
	}
}