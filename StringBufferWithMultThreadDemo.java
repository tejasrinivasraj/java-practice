public class StringBufferWithMultThreadDemo implements Runnable {
 
    StringBuffer strBuffer;
    static volatile float sum = 0;
 
    public StringBufferWithMultThreadDemo() {
        strBuffer = new StringBuffer();
    }
 
    @Override
    public void run() {
 
        for (int i = 0; i < 50000; i++) {
            addChar();
        }
    }
 
    public void addChar() {
        try {
            strBuffer.append("A");
            strBuffer.append("A");
            strBuffer.append("A");
            strBuffer.deleteCharAt(0);
            strBuffer.append("A");
            strBuffer.append("A");
            strBuffer.append("A");
            for (int i = 0; i < 4; i++) {
                strBuffer.deleteCharAt(0);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("A wasn't at index 0 " + e.getMessage());
        }
    }
 
    public static void main(String[] args) {
 
        StringBufferWithMultThreadDemo strBuffrWthThrdDmobj1 = new StringBufferWithMultThreadDemo();
 
        Thread threadOne = new Thread(strBuffrWthThrdDmobj1, "Thread One");
        Thread threadTwo = new Thread(strBuffrWthThrdDmobj1, "Thread Two");
        threadOne.start();
        threadTwo.start();
 
        try {
            threadOne.join();
            threadTwo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final StringBuffer Length: " + strBuffrWthThrdDmobj1.strBuffer.length());
    }
}
