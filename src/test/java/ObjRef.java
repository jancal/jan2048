class Obj {
    String str = "init value";

    public String toString() {
        return str;
    }
}

public class ObjRef {
    Obj aObj = new Obj();
    int aInt = 11;

    public void changeObj(Obj inObj) {
        inObj.str = "changed value";
    }

    public void changePri(int inInt) {
        inInt = 22;
    }

    public static void main(String[] args) {
        ObjRef oRef = new ObjRef();

        System.out.println("Before call changeObj() method: " + oRef.aObj);
        oRef.changeObj(oRef.aObj);
        System.out.println("After call changeObj() method: " + oRef.aObj);

        System.out.println("==================Print Primtive=================");
        System.out.println("Before call changePri() method: " + oRef.aInt);
        oRef.changePri(oRef.aInt);
        System.out.println("After call changePri() method: " + oRef.aInt);

    }
}

/* RUN RESULT
Before call changeObj() method: init value
After call changeObj() method: changed value
==================Print Primtive=================
Before call changePri() method: 11
After call changePri() method: 11

*
*/
