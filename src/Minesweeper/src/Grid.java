class Grid {
    private int size;
    private Cell[][] board;
    Grid() {
        this.size = 3;
        board = new Cell[this.size][this.size];
    }
    Grid(int size) {
        this.size = size;
        board = new Cell[this.size][this.size];
    }
    int getSize() {
        return this.size;
    }
    boolean isMine(Coordinate coordinate) {
        return(this.getObject(coordinate).isMine());
    }
    boolean isFlagged(Coordinate coordinate) {
        return(this.getObject(coordinate).isFlagged());
    }
    boolean isOpened(Coordinate coordinate) {
        return(this.getObject(coordinate).isOpened());
    }
    void setObject(Coordinate coordinate, Cell object) {
        this.board[coordinate.getCoordinateX()][coordinate.getCoordinateY()] = object;
    }
    Cell getObject(Coordinate coordinate) {
        return this.board[coordinate.getCoordinateX()][coordinate.getCoordinateY()];
    }
    String getObjectStatus(Coordinate coordinate) {
        return getObject(coordinate).getStatus();
    }
    void openObject(Coordinate coordinate) {
        this.board[coordinate.getCoordinateX()][coordinate.getCoordinateY()].open();
    }

    void flagObject(Coordinate coordinate) {
        this.getObject(coordinate).flag();
    }
}
