package lcars;

import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import spark.Spark;

import java.lang.management.ManagementFactory;

public class Server {
    public static void main(String[] args) throws InterruptedException {
        Spark.staticFileLocation("public");
        Spark.port(1701);
        //Spark.get("/", (a,b) -> "Hello");
        Spark.init();

        GlobalMemory mem = new SystemInfo().getHardware().getMemory();
        while(true) {
            double usedPerc = 1 - ((double)mem.getAvailable() / (double)mem.getTotal());
            System.out.println(100 * usedPerc);
            Thread.sleep(1000);
            //System.out.println(freePhysicalMemory + " of " + physicalMemorySize + ". Swap: " + freeSwapSize + ". Committed Virtual: " + commitedVirtualMemorySize + ". CPU " + os.getSystemCpuLoad());

        }
    }
}
