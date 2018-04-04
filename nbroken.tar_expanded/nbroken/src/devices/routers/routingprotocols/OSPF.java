package devices.routers.routingprotocols;

public class OSPF extends RoutingProtocol{
	
		/**
	 * 
	 */
	private static final long serialVersionUID = -3326051893867941067L;
	private int PROCESS_ID;
	
    public OSPF() {
        super();
        ADMINISTRATIVE_DISTANCE = 110;
        UPDATE_INTERVAL = 30000;
    }

    public void setProcessID(int PROCESS_ID) {
        this.PROCESS_ID = PROCESS_ID;
    }

    public int getProcessId() {
        return PROCESS_ID;
    }

}
