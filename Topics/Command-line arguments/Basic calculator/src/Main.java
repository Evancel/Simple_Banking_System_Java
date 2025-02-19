class Problem {
    public static void main(String[] args) {
        int num1 = 0;
        int num2 = 0;

        try {
            num1 = Integer.parseInt(args[1]);
            num2 = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException " + e.getMessage());
        }
        switch (args[0]) {
            case "+" -> System.out.println(num1 + num2);
            case "-" -> System.out.println(num1 - num2);
            case "*" -> System.out.println(num1 * num2);
            default -> System.out.println("Unknown operator");
        }
    }
}