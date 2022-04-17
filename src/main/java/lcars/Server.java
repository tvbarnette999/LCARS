package lcars;

import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import spark.Spark;
import java.io.IOException;

public class Server {
    static SocketHandler handler = new SocketHandler();
    public static void main(String[] args) throws InterruptedException {
        Spark.staticFileLocation("public");
        Spark.webSocket("/stream", handler);
        Spark.port(1701);
        Spark.init();

        boolean red = false;
        GlobalMemory mem = new SystemInfo().getHardware().getMemory();
        while(true) {
            double usedPerc = 1 - ((double)mem.getAvailable() / (double)mem.getTotal());
            handler.broadcast(usedPerc + "");
            if (!red && usedPerc >= .85) {
                runPs("color_red.ps1");
                red = true;
            }
            if (red && usedPerc < .8) {
                runPs("color_default.ps1");
                red = false;
            }
            Thread.sleep(1000);
        }
    }

    public static void runPs(String ps) {
        try {
            Runtime.getRuntime().exec("powershell.exe ./" + ps);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
