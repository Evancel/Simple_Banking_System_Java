class Problem {
    public static void main(String[] args) {
        String test = "test";
        for(int i = 0; i < args.length; i++){
            if(args[i].equals(test)){
                System.out.println(i);
                return;
            }
        }
        System.out.println("-1");
    }
}