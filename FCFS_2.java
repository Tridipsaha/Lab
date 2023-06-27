import java.util.PriorityQueue;
import java.util.Scanner;

public class FCFS_2 implements Comparable<FCFS_2> {

    int ID, AT, BT, CT, TAT, WT;
    static float avgwt, avgta;
    static int size = 3;
    static FCFS p[] = new FCFS[size];
    static PriorityQueue<FCFS_2> pq;

    public FCFS_2(int id, int arr, int time) {
        this.AT = arr;
        this.BT = time;
        this.ID = id;
    }

    @Override
    public int compareTo(FCFS_2 other) {
        if (this.AT == other.AT) {
            return 0;
        } else if (this.AT < other.AT) {
            return -1;
        } else {
            return 1;
        }
    }

    public static void main(String[] args) {

        pq = new PriorityQueue<>();

        for (int i = 0; i < size; i++) {
            int arr, time;
            Scanner sc = new Scanner(System.in);

            System.out.println("\nEnter Process" + (i + 1));

            System.out.print("Arrival Time: ");
            arr = sc.nextInt();
            System.out.print("Burst Time: ");
            time = sc.nextInt();

            pq.add(new FCFS_2((i + 1), arr, time));
        }

        calculation_table();
        disp_table();

    }

    public static void calculation_table() {

        for(FCFS_2 p: pq){
            if(p.ID==1){
                p.CT=p.AT+p.BT;
            }else{
                if(p.AT>p.CT){
                    p.CT=p.AT+p.BT;
                }
            }
            p.TAT=p.CT=p.AT;
            p.WT=p.TAT-p.BT;
            avgwt+= p.WT;
            avgta+= p.TAT;
        }
    }

    public static void disp_table(){

            System.out.println(" |Proc| "+" |Arri| "+" |Burs| "+" |Compl| "+" |Turn| "+" |Wait| ");
            for(FCFS_2 p:pq){
                System.out.println("   P"+p.ID+"\t    "+p.AT+"\t   "+ p.BT+"\t    "+p.CT+"\t    "+ p.TAT+"\t    "+p.WT);
                System.out.println();
            }
            System.out.println("\naverage waiting time: "+ (Float)(avgwt/size));     // printing average waiting time.
            System.out.println("average turnaround time:"+(Float)(avgta/size));    // printing average turnaround time.
        }
}
