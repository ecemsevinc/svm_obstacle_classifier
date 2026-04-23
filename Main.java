import java.util.ArrayList;
import java.util.List;



public class Main {
    public static void main(String[] args) {
        List<Point> veri = new ArrayList<>();

        // Sınıf +1: Statik engeller
        veri.add(new Point(1, 1,  1));
        veri.add(new Point(2, 1,  1));
        veri.add(new Point(1, 2,  1));
        veri.add(new Point(2, 2,  1));

        // Sınıf -1: Dinamik engeller
        veri.add(new Point(5, 5, -1));
        veri.add(new Point(6, 5, -1));
        veri.add(new Point(5, 6, -1));
        veri.add(new Point(6, 6, -1));

        SVM svm = new SVM();
        svm.train(veri);
        svm.sonuc(veri);

        System.out.println("(1,1) tahmini: " + svm.predict(1, 1));
        System.out.println("(5,5) tahmini: " + svm.predict(5, 5));
    }
}