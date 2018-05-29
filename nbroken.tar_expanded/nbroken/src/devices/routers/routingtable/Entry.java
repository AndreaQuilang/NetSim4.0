package devices.routers.routingtable;


import devices.addresses.IPAddress;
import devices.addresses.SubnetMask;

import devices.interfaces.Interface;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Entry implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -5780753999566926495L;
	public final static String DIRECTLY_CONNECTED = "C";
    public final static int STATIC_HOP_COUNT = 1;
    public final static int DEFAULT_HOP_COUNT = 1;
    public final static int DIRECTLY_CONNECTED_HOP_COUNT = 0;
    private IPAddress destinationNetwork;
    private SubnetMask mask;
    private IPAddress nextHopAddress;
    private Interface routerInterface;
    private String connectionType = "";
    private int hopCount;
    
    private int cost;
    //Added
    public String name;
    public List<Vertex> sp=new LinkedList<>();
    public Integer d = Integer.MAX_VALUE;
	public Map<Vertex,Integer> adj = new HashMap<>();
	private String areaId;
	//Added 

    public Entry(String destinationNetwork, String mask, String nextHopAddress, int hopCount, int cost) {
        this.destinationNetwork = new IPAddress(destinationNetwork);
        this.mask = new SubnetMask(mask);
        this.nextHopAddress = new IPAddress(nextHopAddress);
        this.hopCount = hopCount;
        this.cost = cost;
        this.name = this.destinationNetwork.toString();
    }

    public int getHopCount() {
        return hopCount;
    }
    
    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public void setRouterInterface(Interface routerInterface) {
        this.routerInterface = routerInterface;
    }

    public void setDestinationNetwork(String destinationNetwork) {
        this.destinationNetwork.setAddress(destinationNetwork);
    }

    public IPAddress getDestinationNetwork() {
        return destinationNetwork;
    }
    
    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setMask(String mask) {
        this.mask.setMask(mask);
    }
    
    public void setArea(String area) {
        this.areaId = area;
    }    

    public SubnetMask getMask() {
        return mask;
    }

    public Interface getRouterInterface() {
        return routerInterface;
    }

    public IPAddress getNextHopAddress() {
        return nextHopAddress;
    }
    
    public String getArea() {
        return areaId;
    }
    
    public int getCost() {
        return cost;
    }
    //Added
    public void add(Vertex v, int d){
	    adj.put(v,d);
	 }
//
//	 public Entry(String name){
//	    this.name=name;
//	 }
	 
	 public String toString(){
	    return name;
	 } 
	 //added
 
}
