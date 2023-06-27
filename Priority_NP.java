import java.util.Scanner;

import javax.sound.midi.Soundbank;
public class Priority_NP {




        int ID,AT,BT,CT,TAT,WT, Priority;
        boolean flag;
        static float avgwt,avgta;
        
        static int size;
        static int ST=0;
        static Priority_NP p[];
        
        public Priority_NP(int id,int arr, int time, int pi){
            this.ID=id;
            this.AT=arr;
            this.BT=time;
            this.flag=false;
            this.Priority=pi;
        }
        
        public static void main(String []args){
    
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter the number of process: ");
            size= sc.nextInt();
            p=new Priority_NP[size];
            

            for(int i=0;i<size;i++){
                int arr,time,pi;

                System.out.println("\nEnter Process"+(i+1));

                System.out.print("Priority: ");
                pi=sc.nextInt();
                System.out.print("Arrival Time: ");
                arr=sc.nextInt();
                System.out.print("Burst Time: ");
                time=sc.nextInt();

                p[i]=new Priority_NP((i+1),arr, time, pi);
            }
            
            sort_table();

            calculation_table();
                    
             disp_table();
        
        }

        public static void sort_table(){
            Priority_NP temp =null;
            for(int i = 0; i<size; i++)
            {
                for(int  j=0;  j<size-(i+1) ; j++)
                {

                    if( p[j].Priority > p[j+1].Priority )
                    {
                        temp=p[j];
                        p[j]=p[j+1];
                        p[j+1]=temp;
                    }
                }
            }
            for(int i = 0; i<size; i++)
            {
                for(int  j=0;  j<size-(i+1) ; j++)
                {
                    if( p[j].Priority  == p[j+1].Priority  )
                    {
                        if(p[j].AT > p[j+1].AT){

                            temp=p[j];
                            p[j]=p[j+1];
                            p[j+1]=temp;
                        }
                    }
                }
            }
        }


        public static void calculation_table(){
        int j=0;
        
        while( j<size){
            for(int i=0;i<size;i++){
                if( ST >= p[i].AT && p[i].flag==false){
                    

                    p[i].CT= ST+ p[i].BT;


                    ST=p[i].CT;
                    p[i].flag=true;
                    
                    p[i].TAT = p[i].CT - p[i].AT ;         
                    p[i].WT = p[i].TAT - p[i].BT ;         
                    avgwt += p[i].WT ;              
                    avgta += p[i].TAT ; 
                    j++;
                    
                    break;
                } else if(ST<p[i].AT && i==size-1){  // Start time increment when arrival time is grater than Starting time
                    ST++;
                }
                            
            }
        }
    
    }          

                
    


        public static void disp_table(){
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println(" |Proc| "+" |Pri| "+" |Arri| "+" |Burs| "+" |Compl| "+" |Turn| "+" |Wait| ");
            for(int i=0;i<size;i++){
                System.out.println("   P"+p[i].ID+"\t   "+p[i].Priority+"\t    "+p[i].AT+"\t   "+ p[i].BT+"\t    "+p[i].CT+"\t    "+ p[i].TAT+"\t    "+p[i].WT);
                System.out.println();
            }
            System.out.println("\naverage waiting time: "+(float) (avgwt/size));     // printing average waiting time.
            System.out.println("average turnaround time:"+(float) (avgta/size));    // printing average turnaround time.
            System.out.println("Throughput: "+(float)(size/avgta));
        }
    }




