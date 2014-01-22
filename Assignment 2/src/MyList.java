/**
 * @author Adam Edgett edgett.a@husky.neu.edu
 * @version 1/21/14
 */
public abstract class MyList {

    abstract boolean isEmpty();

    abstract String get(int index);

    abstract MyList set(int index, String value);

    abstract int size();

    abstract boolean contains(String value);

    public static MyList emptyList() {
        return new EmptyList();
    }

    public static MyList add(MyList list, String str) {
        return new Add(list, str);
    }

    public static boolean isEmpty(MyList list) {
        return list.isEmpty();
    }

    public static String get(MyList list, int index) {
        return list.get(index);
    }

    public static MyList set(MyList list, int index, String value) {
        return list.set(index, value);
    }

    public static int size(MyList list) {
        return list.size();
    }

    public static boolean contains(MyList list, String value) {
        return list.contains(value);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof MyList)) {
            return false;
        }
        MyList objList = (MyList) obj;
        if(MyList.size(objList) == MyList.size(this)) {
            for (int i = 0; i < MyList.size(objList); i++) {
                if(!MyList.get(this, i).equals(MyList.get(objList, i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}