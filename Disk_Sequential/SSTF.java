package Disk_Sequential;

import java.util.Arrays;

import java.util.Scanner;

public class SSTF {

    static int head_position, size, position;
    static int distance, seek_count, cur_track;

    static int tracks[];

    public static void main(String[] args) {

        System.out.print("Enter the current head: ");
        Scanner sc = new Scanner(System.in);

        head_position = sc.nextInt();

        System.out.print("Enter the total number of disk request: ");
        size = sc.nextInt();
        tracks = new int[size];

        System.out.println("Please enter the track position");

        for (int i = 0; i < size; i++) {
            int temp;
            System.out.print("Track " + (i + 1) + "=");
            temp = sc.nextInt();
            tracks[i] = temp;

        }

        Arrays.sort(tracks);
        head_position_fixed();
        calculation_table();

    }




    public static void head_position_fixed() {
        for (int i = 0; i < tracks.length; i++) {
            if (tracks[i] < head_position && head_position < tracks[i + 1]) {
                position = i;
                break;
            }
        }
    }

    //from right to left...........
    public static void calculation_table() {
        for (int i = position; i >= 0; i--) {
            cur_track = tracks[i];

            // calculate absolute distance
            distance = Math.abs(cur_track - head_position);

            // increase the total count
            seek_count += distance;

            // accessed track is now new head
            head_position = cur_track;
        }
        //From left to right...........
        for (int i = position + 1; i < size; i++) {
            cur_track = tracks[i];

            // calculate absolute distance
            distance = Math.abs(cur_track - head_position);

            // increase the total count
            seek_count += distance;

            // accessed track is now new head
            head_position = cur_track;
        }

        System.out.println("\nTotal head movement=" + seek_count);

    }
}
