package Base.etc;

public class IsMapPackage {
    public static boolean isMap (int x, int y, int maxLength) {
        return x >= 0 && x < maxLength && y >= 0 && y < maxLength;
    }
}
