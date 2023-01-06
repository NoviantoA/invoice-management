package novianto.anggoro.invoice.dao;

import novianto.anggoro.invoice.entity.InvoiceType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

@SpringBootTest
@Sql(scripts = {
        "/sql/delete-invoice-type.sql",
        "/sql/insert-inactive-invoice-type.sql"
    })
public class InvoiceTypeDoaTest {

    @Autowired
    InvoiceTypeDao invoiceTypeDao;

    @Test
    public void testInsertInvoiceType() throws Exception{
        InvoiceType invoiceType = new InvoiceType();
        invoiceType.setCode("IT-001");
        invoiceType.setName("Invoice Test Type");
        Assertions.assertNull(invoiceType.getId());
        invoiceTypeDao.save(invoiceType);

        System.out.println("ID : " + invoiceType.getId());
        System.out.println("Create time : " + invoiceType.getCreated());
        System.out.println("Create By : " + invoiceType.getCreateBy());
        System.out.println("Update time : " + invoiceType.getUpdate());
        System.out.println("Update By : " + invoiceType.getUpdatedBy());
        System.out.println("Status record : " + invoiceType.getStatusRecord());

        Assertions.assertNotNull(invoiceType.getId());
        Assertions.assertNotNull(invoiceType.getCreated());
        Assertions.assertNotNull(invoiceType.getCreateBy());
        Assertions.assertNotNull(invoiceType.getUpdate());
        Assertions.assertNotNull(invoiceType.getUpdatedBy());
        Assertions.assertEquals(invoiceType.getCreated(), invoiceType.getUpdate());

        Thread.sleep(1000);
        invoiceType.setName("Test Update");
        invoiceType = invoiceTypeDao.save(invoiceType);

        System.out.println("Create time : " + invoiceType.getCreated());
        System.out.println("Update time : " + invoiceType.getUpdate());
        Assertions.assertNotEquals(invoiceType.getCreated(), invoiceType.getUpdate());

    }

    @Test
    public void testQuerySoftDelete(){
        Long jumlahRecord = invoiceTypeDao.count();
        System.out.println("Jumlah Record : " + jumlahRecord);
        Assertions.assertEquals(1, jumlahRecord);
    }

    @Test
    public void testSoftDelete(){
        InvoiceType invoiceType = invoiceTypeDao.findById("test002").get();
        invoiceTypeDao.delete(invoiceType);

        Long jumlahRecord = invoiceTypeDao.count();
        System.out.println("Jumlah Record : " + jumlahRecord);
        Assertions.assertEquals(0, jumlahRecord);
    }
}
