import com.epam.rd.java.basic.practice7.People;
import org.junit.Assert;
import org.junit.Test;

public class PeopleTest {

    @Test
    public void manListShouldNotBeNull() {

        People people = new People();

        Assert.assertNotNull(people.getManList());

    }

}
