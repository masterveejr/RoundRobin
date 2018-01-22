package roundRobin;

public class bProcesses {

	public int getProcessNum() {
		return processNum;
	}
	public void setProcessNum(int processNum) {
		this.processNum = processNum;
	}
	public int getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(int rtCPU) {
		arrivalTime = arrivalTime - rtCPU;
        if(arrivalTime<0){
            arrivalTime = 0;}
	}
	public int getBurstTime() {
		return burstTime;
	}
	public void setBurstTime() {
		burstTime=burstTime - timeQuantum;

        if(burstTime<0) {
            burstTime=0; }
	}
	public int getTimeQuantum() {
		return timeQuantum;
	}
	public void setTimeQuantum(int timeQuantum) {
		this.timeQuantum = timeQuantum;
	}
	public int getContextSwitch() {
		return contextSwitch;
	}
	public void setContextSwitch() {
		contextSwitch++;
	}
	public int getWaitTime() {
		return waitTime;
	}
	public void setWaitTime(int rtCPU) {
		waitTime = waitTime +rtCPU;
		}
	public int getTurnTime() {
		return turnTime;
	}
	public void setTurnTime() {
		turnTime = this.getWaitTime() + this.getBtInitial();
	}
	public int getAtInitial() {
		return atInitial;
	}
	public void setAtInitial() {
		atInitial = arrivalTime;
	}
	public int getBtInitial() {
		return btInitial;
	}
	public void setBtInitial() {
		btInitial = burstTime;
	}
	private int processNum;
	private int arrivalTime;
	private int burstTime;
	private int timeQuantum;
	private int contextSwitch;
	private int waitTime;
	private int turnTime;
	private int atInitial;
	private int btInitial;
public bProcesses(int PN, int AT, int BT, int TQ){
	processNum = PN;
	arrivalTime = AT;
	burstTime = BT;
	timeQuantum= TQ;
	contextSwitch = 0;
	waitTime = 0;
	turnTime= 0;
	atInitial = 0;
	btInitial = 0;
}
public int verifyIfProcessFinished()
{

    if(getBurstTime()==0) {
        return 1; }
    else
        return 0;
}
public boolean verifyIfReadytoExecute()
{

    if (getArrivalTime() == 0) {      
    	return true; }
    else
        return false;
}

public void SystemPrint(){
	System.out.println("process " + processNum);
	System.out.println("burst time: " + getBurstTime());
	System.out.println("time till in queue: " + getArrivalTime());
	System.out.println("context switch : " + contextSwitch);
	System.out.println("total waiting time: " + getWaitTime());
	System.out.println("total turnaround time: " + getTurnTime());
	System.out.println("*******************************");
}


}
