package devices.routers.routingtable;

import devices.addresses.Area;
import devices.addresses.IPAddress;
import devices.addresses.SubnetMask;

import devices.interfaces.Interface;

import java.io.Serializable;


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
    private Area areaId;
    private int cost;

    public Entry(String destinationNetwork, String mask, String nextHopAddress, int hopCount, int cost, String areaId) {
        this.destinationNetwork = new IPAddress(destinationNetwork);
        this.mask = new SubnetMask(mask);
        this.nextHopAddress = new IPAddress(nextHopAddress);
        this.hopCount = hopCount;
        this.cost = cost;
        this.areaId = new Area(areaId);
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
    
    public void setArea(String areaId) {
        this.areaId.setArea(areaId);
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
    
    public Area getArea() {
        return areaId;
    }
    
    public int getCost() {
        return cost;
    }
    
 
}
