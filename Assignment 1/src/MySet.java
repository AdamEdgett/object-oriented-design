import java.util.ArrayList;
/**
 * Object Oriented Design
 * Assignment 1
 *
 * @author Adam Edgett
 * @date 1/14/14
 */
public class MySet
{
    // ArrayList of numbers (Long) in set
    private ArrayList<Long> content;

    // getter for content
    public ArrayList<Long> getContent()
    {
        return this.content;
    }

    // setter for content
    public void setContent(ArrayList<Long> content)
    {
        this.content = content;
    }

    /**
     * Returns an empty set
     *
     * @return MySet
     */
    public static MySet empty()
    {
        MySet empty = new MySet();
        return empty;
    }

    /**
     * Inserts the given Long into the given MySet
     *
     * @param myset the MySet to be added to
     * @param num the Long to be inserted
     * @return the modified MySet with the new Long added
     */
    public static MySet insert(MySet myset, Long num)
    {
        if(!myset.content.contains(num))
        {
            myset.content.add(num);
        }
        return myset;
    }

    /**
     * Returns the size of the given MySet
     *
     * @param myset the MySet to check the size of
     * @return the size of the MySet
     */
    public static int size(MySet myset)
    {
        return myset.content.size();
    }

    /**
     * Checks to see if the MySet is empty
     *
     * @param myset the MySet to check if empty
     * @return if the MySet is empty
     */
    public static boolean isEmpty(MySet myset)
    {
        return myset.content.size() == 0;
    }

    /**
     * Checks to see if the given MySet contains the given Long
     *
     * @param myset the MySet to check
     * @param num the Long to check for
     * @return if the MySet contains the Long
     */
    public static boolean contains(MySet myset, Long num)
    {
        return myset.content.contains(num);
    }

    /**
     * Checks to see if a MySet is a subset of the other
     *
     * @param set the set to check
     * @param subset the subset to check for
     * @return if the set contains the subset
     */
    public static boolean isSubset(MySet set, MySet subset)
    {
        for(Long l : subset.content)
        {
            if(!MySet.contains(set, l))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Removes the given Long from the MySet
     *
     * @param myset the MySet to remove from
     * @param rem the number to remove
     * @return the modified MySet
     */
    public static MySet remove(MySet myset, Long rem)
    {
        myset.content.remove(rem);
        return myset;
    }

    /**
     * Joins the two MySets
     *
     * @param set1 the first MySet
     * @param set2 the second MySet
     * @return the joined MySets
     */
    public static MySet join(MySet set1, MySet set2)
    {
        MySet ret = set1;
        for(Long l : set2.content)
        {
            ret = MySet.insert(ret, l);
        }
        return ret;
    }

    /**
     * Returns the intersection of the two MySets
     *
     * @param set1 the first MySet
     * @param set2 the second MySet
     * @return the resulting intersecting MySet
     */
    public static MySet intersect(MySet set1, MySet set2)
    {
        MySet ret = new MySet();
        for(Long l : set2.content)
        {
            if(MySet.contains(set1, l))
            {
                ret = MySet.insert(ret, l);
            }
        }
        return ret;
    }

    /**
     * Replaces a number with another number in the given MySet
     *
     * @param myset the MySet to do the replacement
     * @param orig the number to be replaced
     * @param repl the number to replace
     * @return the modified MySet
     */
    public static MySet replace(MySet myset, Long orig, Long repl)
    {
        myset = MySet.remove(myset, orig);
        myset = MySet.insert(myset, repl);
        return myset;
    }

    /**
     * Converts the MySet to a string
     *
     * @return the string representation of the MySet
     */
    public String toString()
    {
        return "This set contains: " + this.content.toString();
    }

    /**
     * Checks the equivalence of another object
     *
     * @return whether the given object equals this MySet
     */
    public boolean equals(Object obj)
    {
        if(obj == null)
        {
            return false;
        }
        if(obj.getClass().isInstance(MySet.class))
        {
            MySet objSet = (MySet) obj;
            for(int i = 0; i < this.content.size(); i++)
            {
                if(this.content.get(i) != objSet.content.get(i))
                {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Returns a hash code of this MySet
     *
     * @return the hash code of this MySet
     */
    public int hashCode()
    {
        return this.content.hashCode();
    }
}

