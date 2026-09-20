package lw01.prelab;

public abstract class PrintJob implements Chargeable {

    private String id;
    private int pages;

    public PrintJob(String id, int pages) {
        if (pages <= 0) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.pages = pages;
    }

    protected String getId() {
        return id;
    }

    protected int getPages() {
        return pages;
    }

    @Override
    public abstract int calculateCharge();

    public int calculateCharge(int copies) {
        if (copies <= 0) {
            throw new IllegalArgumentException();
        }

        return copies * calculateCharge();
    }

    public String label() {
        return "Print";
    }

    public String summary() {
        return id + " | " + label() + " | " + calculateCharge();
    }

}