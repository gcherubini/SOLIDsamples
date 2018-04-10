interface MachineISP {
    void scan(String doc);
    void print(String doc);
    void fax(String doc);
}

class BasicPrinterISP implements MachineISP {

    @Override
    public void scan(String doc) {
        System.out.println("scan " + doc);
    }

    @Override
    public void print(String doc) {
        System.out.println("print " + doc);
    }

    @Override
    public void fax(String doc) {
        // ISP violation: this printer not send fax
    }
}

// better impl below

interface Printer {
    void print(String doc);
}
interface Scanner {
    void scan(String doc);
}
interface Fax {
    void fax(String doc);
}

interface MultiFunctional extends Printer, Scanner, Fax {
}

class BasicPrinter implements Printer, Scanner {
    @Override
    public void scan(String doc) {
        System.out.println("scan " + doc);
    }

    @Override
    public void print(String doc) {
        System.out.println("print " + doc);
    }
}

class MultiFunctionalPrinter implements MultiFunctional {
    @Override
    public void print(String doc) {
        System.out.println("print " + doc);
    }

    @Override
    public void scan(String doc) {
        System.out.println("scan " + doc);
    }

    @Override
    public void fax(String doc) {
        System.out.println("fax " + doc);
    }
}