package com.example.elearning;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyFragment_search extends Fragment {
    private List<Course> courseListOut;
    private ListView mListView;
    private ArrayAdapter mAdapter;
    private SearchView mSearchView;
    private List<String> courseName;
    private DataRequest dataRequest;

    @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.search_layout,container,false);
        //获取搜索布局对象
        mSearchView = (SearchView)view.findViewById(R.id.searchView);
        mListView = (ListView)view.findViewById(R.id.listView);
        courseName = new ArrayList<>();
        new MyAsyncTask(mListView,mSearchView).execute();
        return  view;
    }

    class MyAsyncTask extends AsyncTask<Void,Void,List<Course>> {

        private ListView lsView;
        private SearchView shView;
        public MyAsyncTask(ListView lsView2, SearchView shView2){
           lsView = lsView2;
           shView = shView2;
        }

        @Override
        protected List<Course> doInBackground(Void... voids) {
            dataRequest = DataRequest.getInstance();
            dataRequest.test();
            return dataRequest.getCourseList();
        }

        //根据从服务器获取到的数据更新搜索UI界面
        @Override
        protected void onPostExecute(final List<Course> courseList) {
            super.onPostExecute(courseList);
            for(Course course:courseList){
                courseName.add(course.getName());
            }
            mAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, courseName);
            lsView.setAdapter(mAdapter);
            lsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   String name = courseName.get(position);
                   for(Course course:courseList){
                       if(course.getName().equals(name)){
                           Intent intent = new Intent(getActivity(), CourseInfor.class);
                           intent.putExtra("courselist",(Serializable)(course));
                           startActivity(intent);
                       }
                   }
               }
            });
            lsView.setTextFilterEnabled(true);

            shView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }
                @Override
                public boolean onQueryTextChange(String newText) {
                    mAdapter.getFilter().filter(newText);
                    return false;
                }
            });
        }
    }

}
