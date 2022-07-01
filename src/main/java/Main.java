import org.springframework.boot.SpringApplication;
import services.ReadService;
import services.WriteService;

public class Main {

    public static void main(String[] args){
        SpringApplication.run(Main.class, args);                                                                        //  runs the spring application

        ReadService readService = new ReadService();
        Thread readThrd = new Thread(readService);                                                                      //  new thread for read operations

        WriteService writeService = new WriteService();
        Thread writeThrd = new Thread(writeService);                                                                    //  new thread for write operations

        readThrd.start();                                                                                               //  start thread for read operation
        writeThrd.start();                                                                                              //  start thread for write operation
    }
}
