package devices.hosts.consoles;

import devices.Device;
import devices.addresses.IPAddress;

import devices.commands.Command;

import devices.consoles.Console;

import devices.hosts.Host;

import devices.hosts.consoles.commands.HostCommand;
import devices.hosts.consoles.commands.UserCommand;
import devices.interfaces.Interface;
import platform.gui.Graph;
import platform.gui.MainFrame;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class HostConsole extends Console {
    

    /**
	 * 
	 */
	private static final long serialVersionUID = -837096981977289607L;
	private static boolean isOSPF;
	
	public HostConsole(Host host, MainFrame frame) {
        super(host, frame);
        removeKeyBindings();
    }
	
	public void updateIsOSPF(boolean b) {
		isOSPF = b;
	}

    public void initialize() {
        currentPrompt = "[user@localhost ~]$";
        currentMode = Console.USER_MODE;
        availableCommands = HostCommand.USER_MODE_COMMANDS;
    }

    public void processInput(String input) {
        StringTokenizer tokens = new StringTokenizer(input, " ", true);
        int position = 0;
        String command = null;

        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken();

            if (!token.equals(" ")) {
                command = token;

                break;
            }

            position++;
        }

        if (command != null) {
            processUserCommand(command, tokens, position);
        }
    }

    private void processUserCommand(String input, StringTokenizer tokens, int position) {
        int cursorPosition = position;
        Command command = getFullCommand(input, availableCommands, cursorPosition);

        if (command != null) {
            if (command.equals(UserCommand.IFCONFIG)) {
                StringBuffer selectedInterface = new StringBuffer();
                int selectedInterfacePosition = getNextPosition(tokens, selectedInterface);
                cursorPosition += (input.length() + selectedInterfacePosition);

                if (selectedInterface.length() != 0) {
                    StringBuffer address = new StringBuffer();
                    int addressPosition = getNextPosition(tokens, address);
                    cursorPosition += (selectedInterface.length() + addressPosition);

                    if (address.length() != 0) {
                        if (isValidQuartet(address.toString(), cursorPosition)) {
                        }
                    } else {
                        showIncompleteCommandError();
                    }
                } else {
                    showIncompleteCommandError();
                }
            } else if (command.equals(UserCommand.PING)) {
                StringBuffer arg = new StringBuffer();
                int argPosition = getNextPosition(tokens, arg);
                cursorPosition += (input.length() + argPosition);

                if (arg.length() != 0) {
                    if (isValidQuartet(arg.toString(), cursorPosition)) {
                        StringBuffer extras = new StringBuffer();
                        int extrasPosition = getNextPosition(tokens, extras);
                        cursorPosition += (arg.length() + extrasPosition);

                        if (extras.length() == 0) {
                            IPAddress destination = new IPAddress(arg.toString());

                            if (deviceReachable(getDevice(), destination)) {
                            	
                                showReply(destination);
                            } else {
                                showRequestTimedOut(destination);
                            }
                        } else {
                            showInvalidInputError(cursorPosition);
                        }
                    }
                } else {
                    showIncompleteCommandError();
                }
            }else if (command.equals(UserCommand.TRACERT)){

                StringBuffer arg = new StringBuffer();
                int argPosition = getNextPosition(tokens, arg);
                cursorPosition += (input.length() + argPosition);

                if (arg.length() != 0) {
                    if (isValidQuartet(arg.toString(), cursorPosition)) {
                        StringBuffer extras = new StringBuffer();
                        int extrasPosition = getNextPosition(tokens, extras);
                        cursorPosition += (arg.length() + extrasPosition);

                        if (extras.length() == 0) {
                            IPAddress destination = new IPAddress(arg.toString());

                            if (deviceTraceable(getDevice(), destination)) {
                                showTraceReply(destination,1);
                            } else {
                            	showTraceReply(destination,2);
                            }
                        } else {
                            showInvalidInputError(cursorPosition);
                        }
                    }
                }
                }else if(command.equals(UserCommand.TRACERTOSPF)) {
                	
                	StringBuffer arg1 = new StringBuffer();
                    int argPosition1 = getNextPosition(tokens, arg1);
                    cursorPosition += (input.length() + argPosition1);
                    
                    if (arg1.length() != 0) {
                        if (isValidQuartet(arg1.toString(), cursorPosition)) {
                            StringBuffer extras = new StringBuffer();
                            int extrasPosition = getNextPosition(tokens, extras);
                            cursorPosition += (arg1.length() + extrasPosition);
                            
                          
                            if (extras.length() == 0) {
                                IPAddress destination = new IPAddress(arg1.toString());
                                
                                Device dis = getDevice();
                                Graph graph = new Graph();
                                int ctr = 0;
                                
                                for(Device d : getDevice().getDevices()) {
                                	d.setAdj(new HashMap<Device, Integer>());
                                	ctr++;
                                	
                                	for(Interface in: d.getClosedInterfaces()) {
                                		
                                		if(d.DEVICES.contains(in.getConnectedInterface().getDevice())) {
                                			d.addToAdj(in.getConnectedInterface().getDevice(), in.getCost());
                                			
                                //			System.out.println("BEING ADDED TO ADJ OF:" + d.getName());
                                	//		System.out.println(in.getConnectedInterface().getDevice().getName());
                                			//added to adj
                                			//added to adj
                                		}
                                		
                                	}                              	
                                	graph.add(d); //added to graph
                                }
                                
                                LinkedList<Device> sp;
                              
                                sp = graph.dijkstra(dis);
                                showTraceRoute(sp, graph.getV(), arg1.toString());
                                
                                
                              
                            } else {
                                showInvalidInputError(cursorPosition);
                            }
                        }
                    
                }
                
                }else {
                    showIncompleteCommandError();
                }
            	
            	
            }
        }
    }

