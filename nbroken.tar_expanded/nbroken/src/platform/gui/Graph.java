package platform.gui;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import devices.Device;
import jdk.nashorn.internal.runtime.Source;

public class Graph implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5398001777900566461L;
	Set<Device> v = new HashSet<>();
	
	public void add(Device d) {
		// TODO Auto-generated method stub
		v.add(d);
		System.out.println(d.getName());
	
	}
	
	public Set<Device> getV() {
		return v;
	}
	
	public LinkedList<Device> dijkstra(Device source) {
		System.out.println("DIJKSTRA");
		source.setD(0);
		Set<Device> done = new HashSet<>();
		Queue<Device> notDone = new PriorityQueue<>(
                100000,
                new Comparator<Device>(){
                   public int compare(Device v1, Device v2){
                      return (v1.getD()-v2.getD());
                   }
                }
             ); 
		 notDone.add(source);
		 LinkedList<Device> sp = new LinkedList<>();
		 while (notDone.size() != 0) {
			// System.out.println("iterate");
		       Device u = notDone.poll();
		       for (Map.Entry<Device, Integer> pair: u.getAdj().entrySet()) {
		          Device v = pair.getKey();
		          Integer d = pair.getValue();
		          if (!done.contains(v)) {
		        	  
		        	  System.out.println(" device v: "+ pair.getKey().getName());
		        	  //System.out.println("V:" + v.getD());
		        	  System.out.println(" device u: "+ u.getName());
		        	  //System.out.println("U:" + u.getD());
		             if (u.getD() + d < v.getD()) {
		                v.setD(u.getD() + v.getD());
		                sp = new LinkedList<>(u.sp);
		                sp.add(u);
		                v.sp=sp;
		                System.out.println("SP updated");
		             }
		             notDone.add(v);
		          }
		       }
		       done.add(u);
		    }
		 
		 for (Device u: v){
		       System.out.print(u+":");
		       for (Device d: u.sp) {
		          System.out.print("("+d.getName()+","+d.getD()+")");
		       }
		       System.out.println();
		    }
		 	
			return sp;
		 }
	}
	
	
	
	

