package _04_Behavioral;

class MainSy2 {
    public static void main(String[] args) {
        SumPrinter cal = new SumPrinter();

        cal.print(new SimpleSumStrategy(), 10);
        cal.print(new GaussSumStrategy(), 10);
    }
}

class SumPrinter {
    void print(SumStrategy strategy, int N) {
        System.out.printf("The Sum of 1 - %d : ", N);
        System.out.println(strategy.get(N));
    }
}

interface SumStrategy {
    int get(int N);
}

class SimpleSumStrategy implements SumStrategy {

    @Override
    public int get(int N) {
        int sum = N;

        for (int i = 0; i < N; i++) {
            sum += i;
        }

        return sum;
    }
}

class GaussSumStrategy implements SumStrategy{

    @Override
    public int get(int N) {
        return (N+1)*N/2;
    }
}