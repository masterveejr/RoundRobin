package roundRobin;

import java.util.*;

public class Clock {
	private int time;
	public Clock(){
		time =0;
	}
	public void timeSinceStart(int rtCPU){
		time = time + rtCPU;
	}
	public int getTimeSinceStart(){
		return time;
	}
	public void arrivalTimeOfProcessWaiting(ArrayList<bProcesses>processes, int rtCPU){
		for(int b = 0;b < processes.size();b++){
			bProcesses bv = processes.get(b);
			bv.setArrivalTime(rtCPU);
		}
	}
		public void waitingTmeofProcesses(Queue<bProcesses> queue, int rtCPU, bProcesses currentProcess){
		Iterator<bProcesses> iterator = queue.iterator();
		while(iterator.hasNext()){
			bProcesses bv = iterator.next();
			if(bv !=currentProcess){
				bv.setWaitTime(rtCPU);
			}
		}
	}

	
	
}
