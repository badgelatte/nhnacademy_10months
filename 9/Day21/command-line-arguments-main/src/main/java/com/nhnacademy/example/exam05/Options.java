package com.nhnacademy.example.exam05;

import java.util.ArrayList;
import java.util.List;

import com.beust.jcommander.Parameter;

public class Options {
    @Parameter
    private List<String> parameters = new ArrayList<>();

    @Parameter(names = { "-log", "-verbose" }, description = "Level of verbosity")
    public Integer verbose = 1;

    @Parameter(names = "-groups", description = "Comma-separated list of group names to be run")
    public String groups;

    @Parameter(names = "-debug", description = "Debug mode")
    public boolean debug = false;

    @Parameter(names = "-help", description = "Help")
    public boolean help = false;
}
