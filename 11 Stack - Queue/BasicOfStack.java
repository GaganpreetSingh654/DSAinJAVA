import java.util.ArrayList;

public class BasicOfStack {

    static class MyStack {
        ArrayList<Character> list = new ArrayList<>();

        void push(char val) {
            list.add(val);
        }

        char pop() {
            if (isEmpty()) {

                System.out.println("stack is empty");
                return '\0';
            }
            return list.remove(list.size() - 1);
        }

        char peek() {
            if (isEmpty()) {
                System.out.println("stack is empty");
                return '\0';
            }
            return list.get(list.size() - 1);
        }

        boolean isEmpty() {
            return list.isEmpty();
        }
    }

    public static void main(String[] args) {

    }
}