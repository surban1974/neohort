/**
* Creation date: (07/04/2006)
* @author: Svyatoslav Urbanovych svyatoslav.urbanovych@gmail.com 
*/

/********************************************************************************
*
*	Copyright (C) 2005  Svyatoslav Urbanovych
*
* This program is free software; you can redistribute it and/or
* modify it under the terms of the GNU General Public License
* as published by the Free Software Foundation; either version 2
* of the License, or (at your option) any later version.

* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.

* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
*********************************************************************************/

package neohort.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Hashtable;
import java.util.Vector;



public class util_threadContainer {

    protected int state=-1;
    protected Hashtable<String,String> tredHT;
    protected String command;
    protected boolean currentErrorLaunch = true;
    protected boolean wait = true;
    protected Process process;
	protected Vector<mess_element> launch_mess = new Vector<mess_element>();
	protected String dates_input = "";

public class mess_element{
	private String mess;
	private String type;

	public mess_element(){
	}
	public mess_element(String mess, String type){
		this.mess = mess;
		this.type = type;
	}
	public String getMess() {
		return mess;
	}
	public String getType() {
		return type;
	}
}


private class TheThread extends Throwable implements Runnable {
 	private static final long serialVersionUID = 2507266417398787069L;
	private volatile Thread workThread;
    private boolean start = false;
    private util_threadContainer container = null;
//    TheThread() {
//        super();
//    }
    TheThread(util_threadContainer container) {
        super();
        this.container = container;
    }

    public void start() {
        if (workThread == null) {
            workThread = new Thread(this);
            workThread.start();
        }
    }
    public void stop() {
        if (workThread != null) {
            workThread = null;
        }
    }
    public void run() {
		Thread thisThread = Thread.currentThread();
		while (workThread == thisThread) {
	        if (!start) {
 	           start = true;
                try {
                    container.state = 0;
                    try {
	                    Process p;
                        p = Runtime.getRuntime().exec(command);
                        if(p==null){
							container.launch_mess.add(new mess_element("- LAUNCH COMMAND: " + command + " | PROCESS NULL","ERROR"));
                        	throw new Exception();
                        }                    
						
	                    readFromInternal(p);

                       	if (container.wait) p.waitFor();

                       	String sERROR = null;
        				String ERROR = "";
                       	String sINPUT = null;
        				String INPUT = "";

						BufferedInputStream bufferERROR = new BufferedInputStream(p.getErrorStream());
						BufferedReader commandERROR = new BufferedReader(new InputStreamReader(bufferERROR));

						BufferedInputStream bufferINPUT = new BufferedInputStream(p.getInputStream());
						BufferedReader commandINPUT = new BufferedReader(new InputStreamReader(bufferINPUT));

        				boolean nextStream = true;
        				try {
	        				while(nextStream){
		        				boolean nextERROR = ((sERROR = commandERROR.readLine()) != null);
		        				if(nextERROR) ERROR=sERROR;
		        				else ERROR=null;
		        				boolean nextINPUT = ((sINPUT = commandINPUT.readLine()) != null);
		        				if(nextINPUT) INPUT=sINPUT;
		        				else INPUT=null;
								writeToExternal(ERROR,INPUT,p);
		        				nextStream = nextERROR || nextINPUT;
	        				}
        				}catch(Exception e){}

						try{
	                       	p.getInputStream().close();
						}catch(Exception e){}
						try{
							p.getOutputStream().close();
						}catch(Exception e){}
						try{
							p.getErrorStream().close();
						}catch(Exception e){}

//						if (container.wait)
							

                        container.process = p;
                        container.currentErrorLaunch = true;

                    } catch (Exception e) {
                        container.currentErrorLaunch = false;
						container.launch_mess.add(new mess_element(e.toString(),"ERROR"));   
//                        new bsControllerException(e.toString(),iStub.log_ERROR);                     
                    } catch (Throwable t) {
                        container.currentErrorLaunch = false;
						container.launch_mess.add(new mess_element(t.toString(),"ERROR"));
//						new bsControllerException(t.toString(),iStub.log_ERROR);
                    }
                    container.state = 1;
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
					container.launch_mess.add(new mess_element(e.toString(),"ERROR"));
                    start = true;
                    Thread.currentThread().interrupt();
//					new bsControllerException(e.toString(),iStub.log_ERROR);
                }
	        }
	        if(start) this.stop();
		}
    }

    public void writeToExternal(String ERROR, String INPUT,Process p){
		if(ERROR!=null && !ERROR.equals(""))
           	container.launch_mess.add(new mess_element(ERROR,"ERROR"));
		if(INPUT!=null && !INPUT.equals(""))
			container.launch_mess.add(new mess_element(INPUT,"INFO"));

    }
    public void readFromInternal(Process p){
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
		if(container.dates_input!=null && !container.dates_input.equals("")){
			try{
				out.write(container.dates_input);
				out.close();
			}catch(IOException io){
//				new bsControllerException(io.toString(),iStub.log_ERROR);
			}
		}
    }
}


public util_threadContainer() {
    this.tredHT = new Hashtable<String,String>();
    this.command = "";
}
public util_threadContainer(String comando) {
    this.tredHT = new Hashtable<String,String>();
    this.command = comando;
}
public util_threadContainer(String command, boolean wait) {
    this.tredHT = new Hashtable<String,String>();
    this.command = command;
    this.wait = wait;
}
public util_threadContainer(Hashtable<String,String> tredHTin) {
    this.tredHT = tredHTin;
    this.command = (String) this.tredHT.get("command");
}
public Process getProcess() {
	return process;
}
public int getState() {
	return state;
}
public Hashtable<String,String> getTredHT() {
	return tredHT;
}
public boolean isWait() {
	return wait;
}
public boolean launch() {
	launch_mess = new Vector<mess_element>();
	currentErrorLaunch = true;
    this.state = -1;
    new TheThread(this).start();
    return currentErrorLaunch;
}
public void setCommand(String newCommand) {
	command = newCommand;
}
public void setDates_input(String newDates_input) {
	dates_input = newDates_input;
}
public void setWait(boolean newWait) {
	wait = newWait;
}
public Vector<mess_element> getLaunch_mess() {
	return launch_mess;
}
public void setState(int i) {
	state = i;
}

}
