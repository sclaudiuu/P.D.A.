import mpi.MPI;

public class Main {

    public static void main(String[] args) {
       new Main().run(args);
    }
   
    public void run(String[] args) {
        
        MPI.Init(args);
        
        int size = MPI.COMM_WORLD.Size();
        int[] array = new int[11];
        for(int i = 1; i <= array.length; i++) {
            array[i-1] = i;
        }
        int count = array.length / size;
        int[] recArray = new int[11];
        MPI.COMM_WORLD.Scatter(array , array.length%size, count, MPI.INT, recArray, 0, count, MPI.INT, 0);
        int sum = computeSum(recArray);
        if(MPI.COMM_WORLD.Rank() == 0) {
            for(int i = array.length - array.length%size; i < array.length; i++) {
                sum += array[i];
            } 
        }
        
        int[] sendbuf = {sum};
        int[] recvbuf = new int[1];
        MPI.COMM_WORLD.Reduce(sendbuf , 0, recvbuf, 0, recvbuf.length, MPI.INT, MPI.SUM, 0);
        if(MPI.COMM_WORLD.Rank() == 0) {
            System.out.println(recvbuf[0]);
        }
        MPI.Finalize();
    }

    private int computeSum(int[] recArray) {
        int sum = 0;
        for(int i = 0; i < recArray.length; i++) {
            sum += recArray[i];
        }
        return sum;
    }

}
