package lcars;

import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import spark.Spark;

public class Server {
    static SocketHandler handler = new SocketHandler();
    public static void main(String[] args) throws InterruptedException {
        Spark.staticFileLocation("public");
        Spark.webSocket("/stream", handler);
        Spark.port(1701);
        Spark.init();

        GlobalMemory mem = new SystemInfo().getHardware().getMemory();
        while(true) {
            double usedPerc = 1 - ((double)mem.getAvailable() / (double)mem.getTotal());
            handler.broadcast(usedPerc + "");
            Thread.sleep(1000);
        }
    }
}
