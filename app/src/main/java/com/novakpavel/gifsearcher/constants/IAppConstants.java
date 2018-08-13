package com.novakpavel.gifsearcher.constants;

public interface IAppConstants {
    String GIFS = "GIFS";

    String COMPUTATION_SCHEDULER = "COMPUTATION_SCHEDULER";
    String UI_SCHEDULER = "UI_SCHEDULER";
    String IO_SCHEDULER = "IO_SCHEDULER";

    long CONNECT_TIMEOUT_SECONDS = 15;
    long READ_CONNECT_TIMEOUT_SECONDS = 20;
    long WRITE_CONNECT_TIMEOUT_SECONDS = 20;

    long CACH_SIZE = 20 * 1024 * 1024;

    int NO_STRING_RES = -1;
    int DEFAULT_ITEMS_COUNT_PER_PAGE = 5;
    long DELAY_SPLASH_SCREEN = 3;
}
