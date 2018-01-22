package roundRobin;

import java.util.*;

public class queueCreator {

	private ArrayList<bProcesses> aList;
	private ArrayList<bProcesses> processesComp;
	private Queue<bProcesses> queue;
	private static int numOfProcesses;
	private int timeQuantum;
	private rrCPU rtCPU;
	private Clock clock;

	public queueCreator(ArrayList<bProcesses> bv, int timeQ) {
		aList = bv;
		timeQuantum = timeQ;
		rtCPU = new rrCPU(timeQuantum);
		queue = new LinkedList<bProcesses>();
		clock = new Clock();
		processesComp = new ArrayList<bProcesses>();
		numOfProcesses = 4;

		for (int b = 0; b < bv.size(); b++) {
			bv.get(b).setAtInitial();
			bv.get(b).setBtInitial();
		}
	}

	public void start() {
		queueStart(null);
		roundRobin();

	}

	public void queueStart(bProcesses currentProcess) {
		for (int x = 0; x < aList.size(); x++) {
			bProcesses bv = aList.get(x);

			if (bv.verifyIfReadytoExecute() == true && bv.verifyIfProcessFinished() == 0 && (bv != currentProcess)
					&& !queue.contains(bv)) {
				queue.add(bv);
				System.out.println("new process " + bv.getProcessNum() + " added to queue");

			}
		}
	}

	public void roundRobin() {
		boolean check = false;
		while (!check) {
			bProcesses currentProcess = queue.peek();
			if (currentProcess != null) {
				int CPURT = rtCPU.run(currentProcess);
				clock.timeSinceStart(CPURT);
				clock.arrivalTimeOfProcessWaiting(aList, CPURT);
				clock.waitingTmeofProcesses(queue, CPURT, currentProcess);
				queueStart(currentProcess);
				if (currentProcess.verifyIfProcessFinished() == 1) {
					System.out.println("process " + currentProcess.getProcessNum() + " completed execution");
					aList.remove(currentProcess);
					System.out.println("process " + currentProcess.getProcessNum() + " removed from ready queue");
					processesComp.add(queue.remove());

					if (processesComp.size() == numOfProcesses) {
						this.end();
						check = true;
					}
				} else {
					currentProcess.setContextSwitch();
					currentProcess.SystemPrint();
					queue.remove();
					queue.add(currentProcess);
					queueStart(currentProcess);
				}
			} else {
				clock.timeSinceStart(timeQuantum);
				clock.arrivalTimeOfProcessWaiting(aList, timeQuantum);
				queueStart(null);
			}
		}
	}


	public void end() {
		int waitTimeOfAll = 0;
		int turnTimeOfAll = 0;
		int contextSwitchesOfAll = 0;
		int wait = 0;

		for (int b = 0; b < processesComp.size(); b++) {
			processesComp.get(b).setTurnTime();
		}
		int p1turnTime = processesComp.get(0).getTurnTime();
		int p2turnTime = processesComp.get(1).getTurnTime();
		int p3turnTime = processesComp.get(2).getTurnTime();
		int p4turnTime = processesComp.get(3).getTurnTime();
		int maxTurnTime = Math.max(p1turnTime, Math.max(p2turnTime, Math.max(p3turnTime, p4turnTime)));
		turnTimeOfAll = p1turnTime + p2turnTime + p3turnTime + p4turnTime;

		for (int b = 0; b < processesComp.size(); b++) {
			wait = wait + processesComp.get(b).getWaitTime();
		}
		for (int b = 0; b < processesComp.size(); b++) {
			waitTimeOfAll = wait;
			contextSwitchesOfAll = contextSwitchesOfAll + processesComp.get(b).getContextSwitch();

		}
		double averageWaitTime = waitTimeOfAll / numOfProcesses;
		double averageTurnTime = turnTimeOfAll / numOfProcesses;
		double throughput = ((double) rtCPU.getProcessesComp() / clock.getTimeSinceStart()) * 100.0;
		double runtimeWOCS = clock.getTimeSinceStart() - contextSwitchesOfAll;
		double utilization = (runtimeWOCS / clock.getTimeSinceStart()) * 100.0;
		System.out.println("____Summary_____");
		System.out.println("CPU utilization: " + utilization + "%");
		System.out.println("Throughput: " + throughput + "%");
		System.out.println("Average wait time: " + averageWaitTime);
		System.out.println("Average turn around time: " + averageTurnTime);

	}
}
