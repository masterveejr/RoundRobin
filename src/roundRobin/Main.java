package roundRobin;
import java.io.*;
import java.util.*;
public class Main {

	public static void main(String[] args) {
		int timeQuantum= 1;
		ArrayList<bProcesses> bv = new ArrayList<bProcesses>();
		try{
			String filename = "csvfle.txt";
			File nFile = new File(filename);
			Scanner fileWriter = new Scanner(nFile);

			while(fileWriter.hasNextLine()){
				String line = fileWriter.nextLine();
				int x = 0;
				String[] linew = line.split(",");
				int number= Integer.parseInt(linew[0]);
				int aTime = Integer.parseInt(linew[1]);
				int bTime = Integer.parseInt(linew[2]);
				bv.add(new bProcesses(number, aTime, bTime, timeQuantum));
			}
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		queueCreator b = new queueCreator(bv,timeQuantum);
		b.start();
		System.out.println("end");
	}

}
