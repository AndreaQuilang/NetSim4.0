package devices.routers.routingprotocols;

public class OSPF extends RoutingProtocol{
	
		/**
	 * 
	 */
	private static final long serialVersionUID = -3326051893867941067L;

    public OSPF() {
        super();
        ADMINISTRATIVE_DISTANCE = 110;
        UPDATE_INTERVAL = 30000;
    }

	

}
