/*
   Copyright 2006 Benjamin Livshits

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
/**
    @author Benjamin Livshits <livshits@cs.stanford.edu>
    
    $Id: Basic23.java,v 1.6 2006/04/04 20:00:40 livshits Exp $
 */
package org.example.inter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Locale;


/**
 *  @servlet description="path traversal" 
 *  @servlet vuln_count = "2"
 *  */
public class PathTraversal {
    private static final String FIELD_NAME = "name";

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String s = req.getParameter(FIELD_NAME);
        String name = s;
        RandomAccessFile raf = new RandomAccessFile(name, "rw");
    }
    
    public String getDescription() {
        return "path traversal";
    }
    
    public int getVulnerabilityCount() {
        return 2;
    }
}