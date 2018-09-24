package com.company.dsii.whatsapp.EventB.whatsapp_thread;

import com.company.dsii.whatsapp.EventB.eventb_prelude.*;
import com.company.dsii.whatsapp.EventB.Util.Utilities;
//@ model import org.jmlspecs.models.JMLObjectSet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class machine2{
	private static final Integer max_integer = Utilities.max_integer;
	private static final Integer min_integer = Utilities.min_integer;

	broadcast evt_broadcast = new broadcast(this);
	add_content evt_add_content = new add_content(this);
	forward_one evt_forward_one = new forward_one(this);
	unselect_chat evt_unselect_chat = new unselect_chat(this);
	chatting_first_time evt_chatting_first_time = new chatting_first_time(this);
	forward evt_forward = new forward(this);
	delete_content evt_delete_content = new delete_content(this);
	create_chat_session evt_create_chat_session = new create_chat_session(this);
	delete_chat_session evt_delete_chat_session = new delete_chat_session(this);
	unmute_chat evt_unmute_chat = new unmute_chat(this);
	select_chat evt_select_chat = new select_chat(this);
	mute_chat evt_mute_chat = new mute_chat(this);
	chatting evt_chatting = new chatting(this);
	remove_content evt_remove_content = new remove_content(this);
	add_user evt_add_user = new add_user(this);


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

	/*@ spec_public */ private BRelation<Integer,Integer> contents;

	/*@ spec_public */ private Integer csize;

	/*@ spec_public */ private BRelation<Integer,Integer> inactive;

	/*@ spec_public */ private BRelation<Integer,Integer> muted;

	/*@ spec_public */ private BRelation<Integer,BRelation<Integer,BRelation<Integer,Integer>>> screen;

	/*@ spec_public */ private BRelation<Integer,Integer> toread;

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
		(muted.intersection(active)).equals(BSet.EMPTY) &&
		 toread.domain().isSubset(user) && toread.range().isSubset(user) && BRelation.cross(user,user).has(toread) &&
		toread.isSubset(chat) &&
		inactive.isSubset(chat) &&
		(active.union(toread.union(inactive))).equals(chat) &&
		(active.intersection(toread)).equals(BSet.EMPTY) &&
		(active.intersection(inactive)).equals(BSet.EMPTY) &&
		(csize).compareTo(new Integer(0)) >= 0 &&
		 contents.domain().equals(new Enumerated(new Integer(1),csize)) && contents.range().equals(content) && contents.isaFunction() && BRelation.cross(new Enumerated(new Integer(1),csize),content).has(contents) &&
		content.isSubset(new Best<Integer>(new JMLObjectSet {Integer nInteger c | (\exists Integer e; (contents.has(new Pair<Integer,Integer>(n,c))); e.equals(c))})) &&
		 screen.domain().isSubset(user) && screen.range().isSubset(BRelation.cross(user,((contents).pow()))) && screen.isaFunction() && BRelation.cross(user,BRelation.cross(user,((contents).pow()))).has(screen); */


	/******Getter and Mutator methods definition******/
	/*@ public normal_behavior
	    requires true;
	    assignable \nothing;
	    ensures \result == this.toread;*/
	public /*@ pure */ BRelation<Integer,Integer> get_toread(){
		return this.toread;
	}

	/*@ public normal_behavior
	    requires true;
	    assignable this.toread;
	    ensures this.toread == toread;*/
	public void set_toread(BRelation<Integer,Integer> toread){
		this.toread = toread;
	}

	/*@ public normal_behavior
	    requires true;
	    assignable \nothing;
	    ensures \result == this.inactive;*/
	public /*@ pure */ BRelation<Integer,Integer> get_inactive(){
		return this.inactive;
	}

	/*@ public normal_behavior
	    requires true;
	    assignable this.inactive;
	    ensures this.inactive == inactive;*/
	public void set_inactive(BRelation<Integer,Integer> inactive){
		this.inactive = inactive;
	}

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
	    ensures \result == this.contents;*/
	public /*@ pure */ BRelation<Integer,Integer> get_contents(){
		return this.contents;
	}

	/*@ public normal_behavior
	    requires true;
	    assignable this.contents;
	    ensures this.contents == contents;*/
	public void set_contents(BRelation<Integer,Integer> contents){
		this.contents = contents;
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
	    ensures \result == this.screen;*/
	public /*@ pure */ BRelation<Integer,BRelation<Integer,BRelation<Integer,Integer>>> get_screen(){
		return this.screen;
	}

	/*@ public normal_behavior
	    requires true;
	    assignable this.screen;
	    ensures this.screen == screen;*/
	public void set_screen(BRelation<Integer,BRelation<Integer,BRelation<Integer,Integer>>> screen){
		this.screen = screen;
	}

	/*@ public normal_behavior
	    requires true;
	    assignable \nothing;
	    ensures \result == this.csize;*/
	public /*@ pure */ Integer get_csize(){
		return this.csize;
	}

	/*@ public normal_behavior
	    requires true;
	    assignable this.csize;
	    ensures this.csize == csize;*/
	public void set_csize(Integer csize){
		this.csize = csize;
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
		muted.isEmpty() &&
		toread.isEmpty() &&
		inactive.isEmpty() &&
		csize == 0 &&
		contents.isEmpty() &&
		screen.isEmpty();*/
	public machine2(){
		user = new BSet<Integer>();
		content = new BSet<Integer>();
		chat = new BRelation<Integer,Integer>();
		active = new BRelation<Integer,Integer>();
		chatcontent = new BRelation<Integer,BRelation<Integer,BSet<Integer>>>();
		muted = new BRelation<Integer,Integer>();
		toread = new BRelation<Integer,Integer>();
		inactive = new BRelation<Integer,Integer>();
		csize = 0;
		contents = new BRelation<Integer,Integer>();
		screen = new BRelation<Integer,BRelation<Integer,BRelation<Integer,Integer>>>();

	}
}