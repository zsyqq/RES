package edu.neu.util;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class MyPost implements DBHandler {
    String userId;
    BufferedReader in;
    PrintWriter out;

    /**
     * @param userId
     * @param in
     * @param out
     */
    public MyPost(String userId, BufferedReader in, PrintWriter out) {
        this.userId = userId;
        this.in = in;
        this.out = out;
    }

    /**
     * @throws Exception
     */
    @Override
    public void add() throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * @throws Exception
     */
    @Override
    public void update() throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * @throws Exception
     */
    @Override
    public void search() throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete() throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
