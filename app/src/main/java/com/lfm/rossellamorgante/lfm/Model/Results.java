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
