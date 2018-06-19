package com.example.ferreira.meuappestacio.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.diogosoares.estacioapp.daos.TaskDao;
import com.diogosoares.estacioapp.daos.UserDao;
import com.diogosoares.estacioapp.models.Task;
import com.diogosoares.estacioapp.models.User;

import java.util.List;

public class DbOperations {

    public interface DBOperationsCallBack {
        void userSaverd(Boolean success);

        void userExists(Boolean exists);}

    public interface DBOperationsTaskCallBack {
        void taskSaved(Boolean success);

        void getAllTasks(List<Task> listaTarefas);}

    private static final String TAG = DbOperations.class.getName();

    private Context context;
    private DBOperationsCallBack callback;
    private DBOperationsTaskCallBack taskCallback;

    public DbOperations(Context context) {
        this.context = context;
    }

    public void saveUser(User user, DBOperationsCallBack callback){
        this.callback = callback;
        new saveUserTask().execute(user); }

    public void saveTask(Task task, DBOperationsTaskCallBack callback){
        this.taskCallback = callback;
        new saveTaskTask().execute(task); }

    public void getAllTasks(DBOperationsTaskCallBack callBack){
        this.taskCallback = callBack;
        new getAllTasksTask().execute(); }

    public void queryAuthenticatedUser(String user, String pass, DBOperationsCallBack callback){
        this.callback = callback;
        new findUserTask().execute(user, pass);
    }

    private class saveUserTask extends AsyncTask<User, Void, Void> {
        @Override
        protected Void doInBackground(User... users) {
            try {
                UserDao userDao = AppDatabase.getInstance(context).userDao();
                userDao.insertAll(users);
                if (callback != null) {
                    callback.userSaverd(true); }
            } catch (Exception e) {
                Log.e("saveUserTask", e.getMessage());
                if (callback != null) {
                    callback.userSaverd(false); } }
            return null; }}

    private class saveTaskTask extends AsyncTask<Task, Void, Void> {
        @Override
        protected Void doInBackground(Task... tasks) {
            try {
                TaskDao taskDao = AppDatabase.getInstance(context).taskDao();
                taskDao.insertAll(tasks);
                if (taskCallback != null) {
                    taskCallback.taskSaved(true); }
            } catch (Exception e) {
                Log.e("saveTaskTask", e.getMessage());
                if (taskCallback!= null) {
                    taskCallback.taskSaved(false); } }
            return null; }}

    private class findUserTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            try {
                UserDao userDao = AppDatabase.getInstance(context).userDao();
                User user = userDao.getAuthenticatedUser(params[0], params[1]);
                if (callback != null) {
                    callback.userExists(user != null); }
            } catch (Exception e) {
                Log.e("queryUser", e.getMessage());
                if (callback != null) {
                    callback.userExists(false); } }
            return null; }}

    private class getAllTasksTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                List<Task> lista = AppDatabase.getInstance(context).taskDao().getAll();
                if(taskCallback != null) {
                    taskCallback.getAllTasks(lista); }
            } catch (Exception e) {
                Log.e("queryUser", e.getMessage()); }
            return null; }}}