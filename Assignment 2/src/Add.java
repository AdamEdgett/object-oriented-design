/**
 * @author Adam Edgett edgett.a@husky.neu.edu
 * @version 1/21/14
 */
public class Add extends MyList {
    MyList list;
    String value;

    Add(MyList list, String value) {
        this.list = list;
        this.value = value;
    }

    boolean isEmpty() {
        return false;
    }

    String get(int index) {
        if(index == 0) {
            return this.value;
        }
        else {
            return MyList.get(this.list, index - 1);
        }
    }

    MyList set(int index, String setVal) {
        if(index == 0) {
            this.value = setVal;
            return this;
        }
        else {
            MyList setList =  MyList.set(this.list, index - 1, setVal);
            return new Add(setList, this.value);
        }
    }

    int size() {
        return MyList.size(this.list) + 1;
    }

    boolean contains(String contVal) {
        if(this.value == contVal) {
            return true;
        }
        else {
            return MyList.contains(this.list, contVal);
        }
    }

    public String toString() {
        String ret = "[" + this.value.toString();
        if (!MyList.isEmpty(this.list)) {
            ret += ", ";
            ret += this.list.toString()
                    .replace("[", "")
                    .replace("]", "");
        }
        ret += "]";
        return ret;
    }

    public int hashCode() {
        return this.value.hashCode() + this.list.hashCode();
    }
}