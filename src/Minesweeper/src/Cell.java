class Cell {
    protected String status = "closed";
    String getStatus() {
        return this.status;
    }
    boolean isMine() {
        return false;
    }
    void open() {
        this.status = "opened";
    }
    void flag() {
        this.status = "flagged";
    }
    boolean isFlagged(){
        return this.status.equals("flagged");
    }
    boolean isOpened() {
        return this.status.equals("opened");
    }
}
