import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
import java.math.*;

def call() {
    OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

    def cpuLoad = osBean.getSystemCpuLoad();
    def freeMemory  = osBean.getFreePhysicalMemorySize();
    def totalMemory = osBean.getTotalPhysicalMemorySize();
    def usedMemory  = (totalMemory - freeMemory) / totalMemory;
    
    println "CPU Load:     ${(cpuLoad*100.0).round(2)}%"
    println "Memory Usage: ${(usedMemory*100.0).round(new MathContext(3))}%"
}