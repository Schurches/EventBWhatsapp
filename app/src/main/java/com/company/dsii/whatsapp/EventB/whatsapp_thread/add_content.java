package whatsapp_thread; 

import eventb_prelude.*;
import Util.Utilities;

public class add_content extends Thread{
	/*@ spec_public */ private machine0 machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public add_content(machine0 m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> machine.CONTENT.difference(machine.get_content()).has(c); */
	public /*@ pure */ boolean guard_add_content( Integer c) {
		return machine.CONTENT.difference(machine.get_content()).has(c);
	}

	/*@ public normal_behavior
		requires guard_add_content(c);
		assignable machine.content;
		ensures guard_add_content(c) &&  machine.get_content().equals(\old((machine.get_content().union(new BSet<Integer>(c))))); 
	 also
		requires !guard_add_content(c);
		assignable \nothing;
		ensures true; */
	public void run_add_content( Integer c){
		if(guard_add_content(c)) {
			BSet<Integer> content_tmp = machine.get_content();

			machine.set_content((content_tmp.union(new BSet<Integer>(c))));

			System.out.println("add_content executed c: " + c + " ");
		}
	}

	public void run() {
		while(true) {
			Integer c = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			machine.lock.lock(); // start of critical section
			run_add_content(c);
			machine.lock.unlock(); // end of critical section
		}
	}
}
