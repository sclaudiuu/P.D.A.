import java.util.Random;

import mpi.MPI;

public class Main {

    private static final int VALUE = 0;
    private static final int RANK = 1;

    public static void main(String[] args) {
       new Main().run(args);
    }

    private int value;
    private int me;
    
    public void run(String[] args) {
        value = new Random().nextInt(100);
        
        
        MPI.Init(args);
        
        me = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        System.out.println("Me :" + me + " " + "value : " + value);
        if(me == 0) {
            int[] pair = {me, value};
            MPI.COMM_WORLD.Send(pair, 0, pair.length, MPI.INT, (me+1)%size, 0);
            MPI.COMM_WORLD.Recv(pair, 0, pair.length, MPI.INT, MPI.ANY_SOURCE, 0);
            pair = comparePair(pair);
            System.out.println("Max value : " + pair[VALUE]);
            System.out.println("Rank :" + pair[RANK]);
        }
        else {
            int[] pair = new int[2];
            MPI.COMM_WORLD.Recv(pair, 0, pair.length, MPI.INT, MPI.ANY_SOURCE, 0);
        
            pair = comparePair(pair);
            MPI.COMM_WORLD.Send(pair, 0, pair.length, MPI.INT, (me + 1) % size, 0);
        }
        
        MPI.Finalize();
    }

    private int[] comparePair(int[] pair) {
        int[] newPair = new int[pair.length];
        newPair[VALUE] = pair[VALUE];
        newPair[RANK] = pair[RANK];
        
        if(pair[VALUE] < value) {
            newPair[VALUE] = value;
            newPair[RANK] = me;
        }
        else if (pair[VALUE] == value) {
            if(pair[RANK] < me) {
                newPair[RANK] = me;
            }
        }
        return newPair;
    }

}
