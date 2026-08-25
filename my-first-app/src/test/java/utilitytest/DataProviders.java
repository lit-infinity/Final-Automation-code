package utilitytest;

import org.testng.annotations.DataProvider;

public class DataProviders {

    // ✅ For invalid login testing
    @DataProvider(name = "invalidLoginData") //parallel = true if want to run put inside the bracket to run all data parallel
    public static Object[][] getInvalidLoginData() {
        return new Object[][] {
            { "wrong@test.com",  "wrongpass"  },
            { "admin@test.com",  "wrongpass"  },  // valid email, wrong pass
            { "wrong@test.com",  "pass123"    },  // wrong email, valid pass

        };
    }

    // ✅ For valid login testing
    @DataProvider(name = "validLoginData")
    public static Object[][] getValidLoginData() {
        return new Object[][] {
            { "admin@test.com", "pass123" },
            { "user@test.com",  "abc456"  }
        };
    }

   
}