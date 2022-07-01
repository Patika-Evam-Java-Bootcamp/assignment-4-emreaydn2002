package services;

import static java.lang.System.out;
public class WriteService implements Runnable{

    @Override
    public void run() {
        synchronized (ReadService.syncObject){
            out.println(Thread.currentThread().getName() + " is waiting ...");

            //  Below try catch block works until Read process finish and wait for sync object notify.
            while (!ReadService.isReadProcessDone) {
                try {
                    ReadService.syncObject.wait();
                    out.println( Thread.currentThread().getName() +" is waiting");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //Write read objects from student list
            ReadService.studentList.stream().forEach(out::println);
        }
    }
}
