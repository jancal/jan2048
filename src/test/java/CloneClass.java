/**
 * CloneClass
 *
 * @author Jancal
 * @date 2017/1/19
 */
class CloneClass implements Cloneable {
    public int aInt;

    public Object clone() {
        CloneClass o = null;
        try {
            o = (CloneClass) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }

    public static void main(String[] args) {
        CloneClass a = new CloneClass();
        a.clone();
    }

}
