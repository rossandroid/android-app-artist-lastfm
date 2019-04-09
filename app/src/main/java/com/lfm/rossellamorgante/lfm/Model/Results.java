/*
 * Results
 * This Application implements the MVVM Pattern
 *
 * This Class is used to parse the entire data form server.
 */
package com.lfm.rossellamorgante.lfm.Model;

import java.util.List;

public class Results {
    public Result results;

    public class Result{
        public Artistmatches artistmatches ;
    }

    public class Artistmatches{
        public List<Artist> artist ;
    }
}
