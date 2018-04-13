package fcc.com.mydynamodbdemo.dao;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;

/**
 * Created by wangq on 2018/4/13.
 */

public interface OnGetStudentsFinishedListener {
    void onRetreiveData( PaginatedList result );
    void onError( String message );
}
