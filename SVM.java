import java.util.List;

class SVM {
    double w1 = 0, w2 = 0, b = 0;

    void train(List<Point> points) {
        double lambda = 0.01;
        int t = 1;

        for (int iter = 0; iter < 1000; iter++) {
            for (Point p : points) {
                double eta = 1.0 / (lambda * t);

                // w'yu küçült → marjin büyür
                w1 = (1 - eta * lambda) * w1;
                w2 = (1 - eta * lambda) * w2;

                // Hinge loss ihlali varsa düzelt
                if (p.label * (w1 * p.x + w2 * p.y + b) < 1) {
                    w1 += eta * p.label * p.x;
                    w2 += eta * p.label * p.y;
                    b  += eta * p.label;
                }
                t++;
            }
        }
    }

    int predict(double x, double y) {
        return (w1 * x + w2 * y + b) >= 0 ? 1 : -1;
    }

    void sonuc(List<Point> points) {
        double margin = 2.0 / Math.sqrt(w1 * w1 + w2 * w2);
        System.out.println("Sınır: " + w1 + "x + " + w2 + "y + " + b + " = 0");
        System.out.println("Güvenlik koridoru genişliği: " + margin);

        int dogru = 0;
        for (Point p : points) {
            if (predict(p.x, p.y) == p.label) dogru++;
        }
        System.out.println("Doğruluk: " + dogru + "/" + points.size());
    }
}
