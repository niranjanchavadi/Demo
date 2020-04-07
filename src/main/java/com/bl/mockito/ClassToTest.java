package com.bl.mockito;

public class ClassToTest {
    private final MyDataBase myDataBase;

    public ClassToTest(MyDataBase myDataBase){
        this.myDataBase=myDataBase;
    }

    public boolean query(String query) {
        return this.myDataBase.query(query);
    }
}

