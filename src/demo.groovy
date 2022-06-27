import org.nordcloud.secretScan

class Demo {
    static void main(String[] args) {
        def scanner = new secretScan()
        def keywords = ["password", "hosts"]
        scanner.scanDirectory("/etc", keywords)
    }
}