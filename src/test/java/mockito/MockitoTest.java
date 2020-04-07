package mockito;

import com.bl.mockito.*;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.ArgumentMatchers.booleanThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockitoTest {

    @Mock
    MyDataBase dataBaseMock;

    @Rule
    public MockitoRule mockitoRule= MockitoJUnit.rule();

    @Test
    public void testQuery(){
        ClassToTest t=new ClassToTest(dataBaseMock);
        when(dataBaseMock.query("* from t")).thenReturn(true);
        boolean check=t.query("* from t");
        Assert.assertTrue(check);
    }

    @Test
    public void customerReaderTest(){
        Customer sampleCustomer = new Customer("Susan","Ivanova");
        EntityManager entityManager=mock(EntityManager.class);
        when(entityManager.find(Customer.class,1L)).thenReturn(sampleCustomer);
        CustomerReader customerReader=new CustomerReader();
        customerReader.setEntityManager(entityManager);
        String fullName=customerReader.findFullName(1L);
        Assert.assertEquals("Susan Ivanova",fullName);


    }
}