import java.io.IOException;


    /* This class is intended for automatic invocation of WinAppDriver.exe,
     * which is needed to interact with the any Windows desktop app. 
     */

public class WinAppDriver {

    private static Process process = null;
    
    public static void startUp() {
        System.out.println("Invoking WAD");

        String wadServerPath = "C:\\yourPath\\WinAppDriver.exe";
        ProcessBuilder builder = new ProcessBuilder(wadServerPath).inheritIO();
        try {
            process = builder.start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (process.isAlive()) {
            System.out.println("WAD has been started successfully!");
        }
    }

    public static void kill() throws IOException {
        System.out.println("Disabling WAD"); 
        if (process != null) {
                process.destroy();
                process.getOutputStream().close();
                Runtime.getRuntime().exec("taskkill /F /IM WinAppDriver.exe");
        } else {System.out.println("You have to call wadInvoke() method first");}
    }
}
