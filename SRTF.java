import java.util.Scanner;
public class SRTF {


        int ID,AT,BT,CT,TAT,WT,RT ;
    
        static float avgwt,avgta;
        
        static int size, completedProcesses=0;
        static int ST=0;
        static SRTF p[];
        
        public SRTF(int id,int arr, int time){
            this.ID=id;
            this.AT=arr;
            this.BT=time;
            this.RT =time;
            this.CT=0;
        }
        
        public static void main(String []args){
    
            Scanner sc = new Scanner(System.in);

            System.out.println("Enter the number of process: ");
                size= sc.nextInt();
                p=new SRTF[size];

            for(int i=0;i<size;i++){
                int arr,time;

                System.out.println("\nEnter Process"+(i+1));

                System.out.print("Arrival Time: ");
                arr=sc.nextInt();
                System.out.print("Burst Time: ");
                time=sc.nextInt();

                p[i]=new SRTF((i+1),arr, time);
            }
            
            sort_table();

            calculation_table();
                    
            disp_table();
        
        }

        public static void sort_table(){
            SRTF temp =new SRTF(0, 0, 0);
            for(int i = 0; i<size; i++)
            {

                for(int  j=0;  j<size-(i+1) ; j++)
                {

                    if( p[j].RT  > p[j+1].RT  )
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
                    if( p[j].RT  == p[j+1].RT  )
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
        
            while(completedProcesses<size){

            
            for(int i=0;i<size;i++){
                if( ST >= p[i].AT && p[i].RT !=0){

                    p[i].CT= ST+1;

                    ST++;
                    p[i].RT --;
                    
                    if(p[i].RT ==0){
                        completedProcesses++;
                        p[i].TAT = p[i].CT - p[i].AT ;         
                        p[i].WT = p[i].TAT - p[i].BT ; 
                        avgta+=p[i].TAT;
                        avgwt+=p[i].WT;
                    }
                    sort_table();
                     break;
                    
                } else if(ST<p[i].AT && i==size-1){  // Start time increment when arrival time is grater than Starting time
                    ST++;
                    break;
                } 
            }        
                
            }
        }          
                
    


        public static void disp_table(){

            System.out.println(" |Proc| "+" |Arri| "+" |Burs| "+" |Compl| "+" |Turn| "+" |Wait| ");
            for(int i=0;i<size;i++){
                System.out.println("   P"+p[i].ID+"\t    "+p[i].AT+"\t   "+ p[i].BT+"\t    "+p[i].CT+"\t    "+ p[i].TAT+"\t    "+p[i].WT);
                System.out.println();
            }
            System.out.println("\naverage waiting time: "+(float) (avgwt/size));     // printing average waiting time.
            System.out.println("average turnaround time:"+(float) (avgta/size));    // printing average turnaround time.
            System.out.println("Throughput: "+(float)(size/avgta));
        }
    }






