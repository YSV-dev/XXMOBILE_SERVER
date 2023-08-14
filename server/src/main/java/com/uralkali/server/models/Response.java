/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uralkali.server.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author brzsmg
 */
public class Response implements Serializable {
    public String result = "success"; //error,failure
    public List<Object> data = new ArrayList<Object>();
}
