package roundRobin;

public class rrCPU {
	private int timeQuantum;
	private int processesComp;
	public rrCPU(int timeq){
		timeQuantum = timeq;
		processesComp = 0;
	}
	public int run(bProcesses bv){
		if(bv.getBurstTime()>timeQuantum){
			bv.setBurstTime();
			return timeQuantum;
		}
		else if(bv.getBurstTime()<timeQuantum){
			int num = bv.getBurstTime();
			bv.setBurstTime();;
			processesComp++;
			return num;
		}
		else{
			bv.setBurstTime();
			processesComp++;
			return timeQuantum;
		}
	}
	public int getProcessesComp(){
		return processesComp;
	}
}
