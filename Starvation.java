import java.util.Scanner;

public class Starvation {
    int ID,AT,BT,CT,TAT,WT,Aging;
        boolean flag;
        static float avgwt,avgta;
        
        static int size, completedProcesses=0;
        static int ST=0;
        static Starvation p[];
        
        public Starvation(int id,int arr, int time){
            this.ID=id;
            this.AT=arr;
            this.BT=time;
            this.CT=0;
            this.Aging=2;
            this.flag=false;
        }
        
        public static void main(String []args){
    
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter the number of process: ");
                size= sc.nextInt();
                p=new Starvation[size];

            for(int i=0;i<size;i++){
                int arr,time;

                System.out.println("\nEnter Process"+(i+1));

                System.out.print("Arrival Time: ");
                arr=sc.nextInt();
                System.out.print("Burst Time: ");
                time=sc.nextInt();

                p[i]=new Starvation((i+1),arr, time);
            }
            
            

            calculation_table();
                    
            disp_table();
        
        }

        private static void disp_table() {
        }

        private static void calculation_table() {
            int j=0;
        
        while( j<size){
            for(int i=0;i<size;i++){
                if( ST >= p[i].AT && p[i].flag==false){

                    

                    if(p[i].Aging>0 && p[i].Aging<=2){
                        p[i].Aging--;
                    }

                    if(p[i].Aging==0){
                        
                        p[i].CT= ST+ p[i].BT;
    
    
                        ST=p[i].CT;
                        p[i].flag=true;
    
                        p[i].TAT = p[i].CT - p[i].AT ;         
                        p[i].WT = p[i].TAT - p[i].BT ;         
                        avgwt += p[i].WT ;              
                        avgta += p[i].TAT ; 
                        j++;
                    }

                    
                    
                } else if(ST<p[i].AT && i==size-1){  // Start time increment when arrival time is grater than Starting time
                    ST++;
                }
                            
            }
        }
        }

        
}


// import java.util.ArrayList;
// import java.util.List;

// class Process {
//     private String name;
//     private int priority;
//     private int waitingTime;

//     public Process(String name, int priority) {
//         this.name = name;
//         this.priority = priority;
//         this.waitingTime = 0;
//     }

//     public String getName() {
//         return name;
//     }

//     public int getPriority() {
//         return priority;
//     }

//     public int getWaitingTime() {
//         return waitingTime;
//     }

//     public void incrementWaitingTime() {
//         waitingTime++;
//     }

//     public void incrementPriority() {
//         priority++;
//     }

//     @Override
//     public String toString() {
//         return "Process: " + name + ", Priority: " + priority + ", Waiting Time: " + waitingTime;
//     }
// }

// public class Starvation {
//     public static void main(String[] args) {
//         List<Process> processes = new ArrayList<>();
//         processes.add(new Process("Process A", 1));
//         processes.add(new Process("Process B", 2));
//         processes.add(new Process("Process C", 3));
//         processes.add(new Process("Process D", 4));
//         processes.add(new Process("Process E", 5));

//         int time = 0;
//         int agingThreshold = 10;
//         int agingIncrement = 1;

//         while (true) {
//             System.out.println("Time: " + time);

//             // Check waiting time and increment priority
//             for (Process process : processes) {
//                 process.incrementWaitingTime();

//                 if (process.getWaitingTime() >= agingThreshold) {
//                     process.incrementPriority();
//                     System.out.println("Aging applied to: " + process.getName() + ", New Priority: " + process.getPriority());
//                 }
//             }

//             // Select process with highest priority for execution
//             Process highestPriorityProcess = null;
//             for (Process process : processes) {
//                 if (highestPriorityProcess == null || process.getPriority() > highestPriorityProcess.getPriority()) {
//                     highestPriorityProcess = process;
//                 }
//             }

//             // Break the loop if all processes have finished execution
//             if (highestPriorityProcess == null) {
//                 break;
//             }

//             // Print and remove the selected process from the list
//             System.out.println("Executing: " + highestPriorityProcess);
//             processes.remove(highestPriorityProcess);

//             time++;
//         }

//         System.out.println("All processes have finished execution.");
//     }
// }
