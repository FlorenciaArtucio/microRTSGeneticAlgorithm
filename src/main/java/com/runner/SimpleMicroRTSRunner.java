package com.runner;

import rts.MicroRTS;
import rts.GameState;
import rts.PhysicalGameState;
import ai.core.AI;

public class SimpleMicroRTSRunner {
    public static void main(String[] args) {
        try {
            MicroRTS.main(args);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}