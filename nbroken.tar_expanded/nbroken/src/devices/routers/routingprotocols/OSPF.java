package devices.routers.routingprotocols;

public class OSPF extends RoutingProtocol{
	
		private static final long serialVersionUID = 121212121212L;
		private int ASNumber;

	    public OSPF() {
	        super();
	        ADMINISTRATIVE_DISTANCE = 110;
	        UPDATE_INTERVAL = 30000;
	    }

	    public void setASNumber(int ASNumber) {
	        this.ASNumber = ASNumber;
	    }

	    public int getASNumber() {
	        return ASNumber;
	    }
	

}
