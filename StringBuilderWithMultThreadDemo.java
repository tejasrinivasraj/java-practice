public class StringBuilderWithMultThreadDemo implements Runnable {
 
    StringBuilder strBuilder;
 
    public StringBuilderWithMultThreadDemo() {
        strBuilder = new StringBuilder();
    }
 
    @Override
    public void run() {
 
        for (int i = 0; i < 50000; i++) {
            addChar();
        }
    }
 
    public void addChar() {
        try {
            strBuilder.append("A");
            strBuilder.append("A");
            strBuilder.append("A");
            strBuilder.deleteCharAt(0);
            strBuilder.append("A");
            strBuilder.append("A");
            strBuilder.append("A");
            for (int i = 0; i < 4; i++) {
                strBuilder.deleteCharAt(0);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("A wasn't at index 0 " + e.getMessage());
        }
    }
 
    public static void main(String[] args) {
 
        StringBuilderWithMultThreadDemo strBldrWthThrdDmobj1 = new StringBuilderWithMultThreadDemo();
 
        Thread threadOne = new Thread(strBldrWthThrdDmobj1, "Thread One");
        Thread threadTwo = new Thread(strBldrWthThrdDmobj1, "Thread Two");
        threadOne.start();
        threadTwo.start();
 
        try {
            threadOne.join();
            threadTwo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final StringBuilder Length: " + strBldrWthThrdDmobj1.strBuilder.length());
 
    }
}