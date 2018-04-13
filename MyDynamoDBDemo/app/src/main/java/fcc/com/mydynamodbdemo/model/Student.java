package fcc.com.mydynamodbdemo.model;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

@DynamoDBTable(tableName = "student")
public class Student {

    private String mNumber;
    private String mName;
    private String mMemo;

    @DynamoDBHashKey
    @DynamoDBIndexHashKey(attributeName = "number")
    public String getNumber() { return this.mNumber; }

    @DynamoDBAttribute(attributeName = "name")
    public String getName() { return this.mName;   }

    @DynamoDBAttribute(attributeName = "memo")
    public String getMemo() {  return this.mMemo;  }

    public void setNumber(String number) {
        this.mNumber = number;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public void setMemo(String memo) {
        this.mMemo = memo;
    }
}