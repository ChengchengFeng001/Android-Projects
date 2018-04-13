package fcc.com.mydynamodbdemo.dao;

import android.content.Context;
import android.os.AsyncTask;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBScanExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import fcc.com.mydynamodbdemo.model.Student;

/**
 * AsyncTask which retrieve data from dynamoDB
 */

public class LoadStudentsAsyncTask extends AsyncTask<Void,Void,PaginatedList<Student>> {

    private Context mContext;
    private OnGetStudentsFinishedListener mListener;

    public LoadStudentsAsyncTask(Context context, OnGetStudentsFinishedListener listener)
    {
        mContext = context;
        mListener = listener;
    }

    @Override
    protected void onPostExecute(PaginatedList<Student> students) {
        super.onPostExecute(students);
        mListener.onRetreiveData(students);
    }

    @Override
    protected PaginatedList<Student> doInBackground(Void... voids) {
        PaginatedList<Student> students = null;
        try
        {
            CognitoCachingCredentialsProvider provider = new CognitoCachingCredentialsProvider(mContext,
                    "YOUR_POOL_ID",
                    Regions.US_EAST_1);
            AmazonDynamoDBClient client = new AmazonDynamoDBClient(provider);
            DynamoDBMapper mapper = new DynamoDBMapper(client);
            DynamoDBScanExpression expression = new DynamoDBScanExpression();
            students = mapper.scan(Student.class, expression);
        }
        catch (AmazonServiceException e)
        {
            mListener.onError(e.getMessage());
        }
        catch (AmazonClientException e)
        {
            mListener.onError(e.getMessage());
        }
        catch (Exception e)
        {
            mListener.onError(e.getMessage());
        }
        finally
        {
            return students;
        }
    }
}
