import java.util.Random;

class Theater{
    int thearter;
}

class TicketMachine extends Thread{
    Random rand = new Random();
    public String myName;
    public Thread thread;
    public Theater[] c;
    private static final int MAX_MOV = 5;


    TicketMachine(String name, Theater[] c1){
        myName = name;
        c = c1;
        System.out.println("Creating " + myName);
    }
    public void run(){
        System.out.println("Running " + myName);
        try{
            while(true){
                int theRestOfTick = 0;
                boolean isLock = false;
                for (int i=0; i<MAX_MOV; i++){
                    theRestOfTick += c[i].thearter;
                }
                int randMov = rand.nextInt(MAX_MOV);
                int randTick = rand.nextInt(5) + 1;
                int delay = (int)(Math.random()*500);
                System.out.println("Before Lock ");
                for(int i=0; i<MAX_MOV; i++) {
                    System.out.println("Theater" + i + ": " + c[i].thearter);
                }
                if(c[randMov].thearter - randTick >= 0) {
                    c[randMov].thearter -= randTick;
                    isLock = true;
                }
                Thread.sleep(delay);
                int isBuy = rand.nextInt(2);

                if(isBuy == 1 && isLock) {
//                    Thread.sleep(delay);
                    System.out.println("Before " + myName +" Buy Theater: " + (randMov+1) + " = " + ((c[randMov].thearter) + randTick));
                    System.out.println(myName + " Buy theater: " + (randMov+1) + " for " + randTick);
                    for(int i=0; i<MAX_MOV; i++) {
                        System.out.println("Theater " + (i+1) + ": " + c[i].thearter);
                    }
                }else {
//                    System.out.println(myName + " Can't Buy / Not Buy " );
                    if(isLock)
                        c[randMov].thearter += randTick;
                }
            }

        } catch (InterruptedException e) {
            System.out.println("Thread " + myName + " interrupted.");
        }
        System.out.println("Thread " + myName + " exiting.");
    }

    public void start(){
        System.out.println("Start " + myName);
        if(thread == null){
            thread = new Thread(this, myName);
            thread.start();
        }

    }
}

public class Multi {
    private static final int MAX_SEAT = 20;
    private static final int MAX_MOV = 5;
    public static void main(String args[]){
        Theater[] c = new Theater[MAX_MOV];

        for (int i=0; i<MAX_MOV; i++) {
            c[i] = new Theater();
        }

        System.out.println("debug");
        System.out.println(c[0].thearter);

        for(int i=0; i<MAX_MOV; i++) {
            c[i].thearter = MAX_SEAT;
        }
        TicketMachine t1 = new TicketMachine("t1", c);
        TicketMachine t2 = new TicketMachine("t2", c);
        TicketMachine t3 = new TicketMachine("t3", c);
        TicketMachine t4 = new TicketMachine("t4", c);
        TicketMachine t5 = new TicketMachine("t5", c);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
