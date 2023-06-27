package Disk_Sequential;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FCFS {

    static int head_position, size;
    static int cur_track, distance, seek_count;
    static Queue<Integer> readyQueue = new LinkedList<>();

    public static void main(String[] args) {

        System.out.print("Enter the current head: ");
        Scanner sc = new Scanner(System.in);

        head_position = sc.nextInt();

        System.out.print("Enter the total number of disk request: ");
        size = sc.nextInt();
        System.out.println("Please enter the track position");

        for (int i = 0; i < size; i++) {
            int temp;
            System.out.print("Track " + (i + 1) + "=");
            temp = sc.nextInt();
            readyQueue.add(temp);
        }

        calculation_table();

        System.out.println("\nTotal head movement=" + seek_count);
    }

    public static void calculation_table() {
        for (int i = 0; i < size; i++) {
            cur_track = readyQueue.poll();

            // calculate absolute distance
            distance = Math.abs(cur_track - head_position);

            // increase the total count
            seek_count += distance;

            // accessed track is now new head
            head_position = cur_track;
        }
    }

}
